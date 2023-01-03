package org.crumbleworks.forge.crumbprops;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.crumbleworks.forge.crumbprops.annotations.PropertyFile;
import org.crumbleworks.forge.crumbprops.exceptions.AnnotationNotPresentException;
import org.crumbleworks.forge.crumbprops.exceptions.ObjectNotManagedException;
import org.crumbleworks.forge.crumbprops.exceptions.StorageIOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages (loading &amp; storing) property files.
 * 
 * @author Michael Stocker
 * @since 1.0
 */
public class PropertyManager {

    private static final Logger logger = LoggerFactory
            .getLogger(PropertyManager.class);

    // Map<object, PropertyFileProxy>
    private Map<Object, PropertyFileProxy> managedPropertyFiles = new HashMap<>();

    /**
     * Add object to the managed property files.
     * 
     * @param object
     *            an instance of a {@link PropertyFile}-annotated class
     */
    public void add(Object object) {
        if(!object.getClass().isAnnotationPresent(PropertyFile.class)) {
            throw new AnnotationNotPresentException(object.getClass());
        }

        PropertyFileProxy pfp;
        try {
            pfp = new PropertyFileProxy(object,
                    (Converters)object.getClass()
                            .getAnnotation(PropertyFile.class).converter()
                            .getConstructor()
                            .newInstance(),
                    (Storage)object.getClass()
                            .getAnnotation(PropertyFile.class).storage()
                            .getConstructor()
                            .newInstance());
            managedPropertyFiles.put(object, pfp);
        } catch(InstantiationException | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        logger.debug("Added {} to manager {}", object, this);
    }

    /**
     * Remove managed property file from manager.
     * 
     * @param object
     *            object an instance of a {@link PropertyFile}-annotated class
     * @throws ObjectNotManagedException
     */
    public void remove(Object object) {
        Object prevValue = managedPropertyFiles.remove(object);

        if(logger.isDebugEnabled() && (prevValue != null)) {
            logger.debug("Removed {} from manager {}", object, this);
        } else {
            logger.debug("Couldn't remove {}, was not managed by {}", object,
                    this);
        }
    }

    public void removeAll() {
        managedPropertyFiles = new HashMap<>();
        logger.debug("Removed all managed objects from {}", this);
    }

    /**
     * Save managed property file to storage.
     * <p>
     * Storage is defined via the {@link PropertyFile} annotation.
     * 
     * @param object
     *            object an instance of a {@link PropertyFile}-annotated class
     * @throws ObjectNotManagedException
     * @throws StorageIOException
     */
    public void save(Object object)
            throws ObjectNotManagedException, StorageIOException {
        if(!isManaged(object)) {
            throw new ObjectNotManagedException(object);
        }

        managedPropertyFiles.get(object).save();

        logger.debug("Saved {} to storage", object);
    }

    public void saveAll() throws StorageIOException {
        managedPropertyFiles.values().forEach(this::save);
    }

    /**
     * Loads managed property file from storage.
     * <p>
     * Storage is defined via the {@link PropertyFile} annotation.
     * 
     * @param object
     *            object an instance of a {@link PropertyFile}-annotated class
     * @throws ObjectNotManagedException
     * @throws StorageIOException
     */
    public void load(Object object)
            throws ObjectNotManagedException, StorageIOException {
        if(!isManaged(object)) {
            throw new ObjectNotManagedException(object);
        }

        managedPropertyFiles.get(object).load();

        logger.debug("Loaded {} from storage", object);
    }

    public void loadAll() throws StorageIOException {
        managedPropertyFiles.values().forEach(this::load);
    }

    /**
     * Tells if an object is managed by this manager.
     * 
     * @param object
     *            object an instance of a {@link PropertyFile}-annotated class
     * @return <code>true</code> if the object is managed.
     */
    public boolean isManaged(Object object) {
        return managedPropertyFiles.containsKey(object);
    }
}