package org.crumbleworks.forge.crumbprops;

import java.nio.file.Path;
import java.util.Set;

import org.crumbleworks.forge.crumbprops.annotations.PropertyFile;
import org.crumbleworks.forge.crumbprops.exceptions.StorageIOException;

/**
 * Defines methods for writing/reading property-files. The implementation
 * defined on the {@link PropertyFile} annotation is used by the
 * {@link PropertyManager}.
 * 
 * <p>
 * <b>Attention:</b> Implementations need a public no-args constructor!
 * 
 * @see DefaultStorageImpl DefaultStorageImpl for a file-based implementation
 * 
 * @author Michael Stocker
 * @since 1.0
 */
public interface Storage {

	public void write(Set<PropertyField> properties, Path filePath) throws StorageIOException;

	public void read(Set<PropertyField> properties, Path filePath) throws StorageIOException;
}