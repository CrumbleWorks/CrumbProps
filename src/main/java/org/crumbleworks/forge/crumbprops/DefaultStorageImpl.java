/**
 * 
 */
package org.crumbleworks.forge.crumbprops;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Set;

import org.crumbleworks.forge.crumbprops.exceptions.StorageIOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * File-based implementation using Java's {@link Properties}.
 * 
 * @author Michael Stocker
 * @since 1.0
 */
public class DefaultStorageImpl implements Storage {
	private static final Logger logger = LoggerFactory.getLogger(DefaultStorageImpl.class);

	private String propertyMessage = "Created & managed using CrumbProps";

	@Override
	public void write(Set<PropertyField> properties, Path filePath) throws StorageIOException {
		logger.debug("Writing properties to file: {}", filePath.toAbsolutePath());

		Properties propertyMap = new Properties();
		for (PropertyField propertyField : properties) {
			propertyMap.setProperty(propertyField.key(), propertyField.getStringValue());
		}

		try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
			propertyMap.store(fos, propertyMessage);
		} catch (IOException e) {
			throw new StorageIOException(e);
		}
		logger.debug("Wrote properties to: {}", filePath.toAbsolutePath());
	}

	@Override
	public void read(Set<PropertyField> properties, Path filePath) throws StorageIOException {
		logger.debug("Loading properties from file: {}", filePath.toAbsolutePath());

		Properties propertyMap = new Properties();
		try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
			propertyMap.load(fis);
		} catch (IOException e) {
			throw new StorageIOException(e);
		}

		for (PropertyField propertyField : properties) {
			propertyField.setStringValue(propertyMap.getProperty(propertyField.key(), propertyField.defaultValue()));
		}
		logger.debug("Loaded properties from: {}", filePath.toAbsolutePath());
	}
}