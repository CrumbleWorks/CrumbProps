package org.crumbleworks.forge.crumbprops;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import org.crumbleworks.forge.crumbprops.annotations.Property;
import org.crumbleworks.forge.crumbprops.annotations.PropertyFile;
import org.crumbleworks.forge.crumbprops.exceptions.MissingConversionMethodException;
import org.crumbleworks.forge.crumbprops.exceptions.StorageIOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a {@link PropertyFile} annotated class.
 * 
 * @author Michael Stocker
 * @since 1.0
 */
class PropertyFileProxy {
	private static final Logger logger = LoggerFactory.getLogger(PropertyFileProxy.class);

	private final Storage storageInstance;

	private final Path pathToFile;
	private final Set<PropertyField> propertyFields;

	public PropertyFileProxy(Object classInstance, Converters convertersInstance, Storage storageInstance) {
		this.storageInstance = storageInstance;

		HashMap<Class<?>, Method> convertToMethods = new HashMap<Class<?>, Method>();
		HashMap<Class<?>, Method> convertFromMethods = new HashMap<Class<?>, Method>();
		Class<?> convertersClass = convertersInstance.getClass();
		for (Method method : convertersClass.getMethods()) {
			if (method.getName().matches("^convertTo.*$")) {
				try {
					convertToMethods.put(method.getReturnType(), method);
					convertFromMethods.put(method.getReturnType(), convertersClass
							.getMethod("convertFrom" + method.getName().substring(9), method.getReturnType()));
				} catch (NoSuchMethodException | SecurityException e) {
					throw new MissingConversionMethodException(method.getReturnType());
				}
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Found converter methods for the following types: {}",
					Arrays.toString(convertToMethods.keySet().toArray()));
		}

		Set<Field> annotatedFields = Arrays.stream(classInstance.getClass().getFields())
				.filter(f -> f.isAnnotationPresent(Property.class)).collect(Collectors.toSet());
		if (logger.isDebugEnabled()) {
			logger.debug("Found the following fields: {}", Arrays
					.toString(annotatedFields.stream().map(f -> f.getAnnotation(Property.class).key()).toArray()));
		}

		PropertyFile classAnnotation = classInstance.getClass().getAnnotation(PropertyFile.class);
		pathToFile = Paths.get(classAnnotation.fileDir(), classAnnotation.fileName());
		logger.debug("Path of property file pointing to: {}", pathToFile);

		propertyFields = annotatedFields.stream().map(f -> {
			return new PropertyField(classInstance, f, convertersInstance, convertToMethods.get(f.getType()),
					convertFromMethods.get(f.getType()));
		}).collect(Collectors.toSet());
	}

	/* FORWARDS */

	public void save() throws StorageIOException {
		storageInstance.write(propertyFields, pathToFile);
	}

	public void load() throws StorageIOException {
		storageInstance.read(propertyFields, pathToFile);
	}
}