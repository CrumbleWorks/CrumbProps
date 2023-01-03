package org.crumbleworks.forge.crumbprops.exceptions;

import org.crumbleworks.forge.crumbprops.DefaultConvertersImpl;
import org.crumbleworks.forge.crumbprops.annotations.Property;

/**
 * Thrown if there's no conversion method for a specific type.
 * <p>
 * E.g. if a field with a custom type is {@link Property}-annotated, but the
 * {@link DefaultConvertersImpl} is used
 * 
 * @author Michael Stocker
 * @since 1.0
 */
public class MissingConversionMethodException extends RuntimeException {

    private static final long serialVersionUID = -1040902158660359732L;

    public MissingConversionMethodException(Class<?> type) {
        super("Missing convertTo and/or convertFrom method(s) for: "
                + type.getName());
    };
}
