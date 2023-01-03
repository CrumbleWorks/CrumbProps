package org.crumbleworks.forge.crumbprops.exceptions;

import org.crumbleworks.forge.crumbprops.annotations.PropertyFile;

/**
 * Thrown if the manager is told to manage a class that is missing the
 * {@link PropertyFile} annotation.
 * 
 * @author Michael Stocker
 * @since 1.0
 */
public class AnnotationNotPresentException extends RuntimeException {

    private static final long serialVersionUID = -5598853579223488332L;

    public AnnotationNotPresentException(Class<?> type) {
        super(type.getName() + " is missing the "
                + PropertyFile.class.getSimpleName() + " annotation");
    };
}