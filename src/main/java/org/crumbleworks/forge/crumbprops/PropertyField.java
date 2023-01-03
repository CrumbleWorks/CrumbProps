package org.crumbleworks.forge.crumbprops;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.crumbleworks.forge.crumbprops.annotations.Property;
import org.crumbleworks.forge.crumbprops.annotations.PropertyFile;

/**
 * Represents a single {@link Property}-annotated field.
 * 
 * @author Michael Stocker
 * @since 1.0
 */
public class PropertyField {

    private final Object classInstance;
    private final Field field;

    private final Property annotation;

    private final Object convertersInstance;
    private final Method convertToType;
    private final Method convertFromType;

    public PropertyField(Object classInstance, Field field,
            Object convertersInstance, Method convertToType,
            Method convertFromType) {
        this.classInstance = classInstance;
        this.field = field;

        this.annotation = field.getAnnotation(Property.class);

        this.convertersInstance = convertersInstance;
        this.convertToType = convertToType;
        this.convertFromType = convertFromType;
    }

    public String key() {
        return annotation.key();
    }

    public String defaultValue() {
        return annotation.defaultValue();
    }

    public Class<?> type() {
        return field.getType();
    }

    /**
     * @return the value as stored on the managed
     *         {@link PropertyFile}-annotated
     *         class' instance
     */
    public Object getValue() {
        try {
            return field.get(classInstance);
        } catch(IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the value stored on the managed {@link PropertyFile}-annotated
     *         class'
     *         instance, converted <b>to</b> a string. Or the default value,
     *         if any.
     */
    public String getStringValue() {
        try {
            Object value = getValue();
            if(value == null) {
                return defaultValue();
            }

            return (String)convertFromType.invoke(convertersInstance, value);
        } catch(IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the value on the managed {@link PropertyFile}-annotated class'
     * instance.
     */
    public void setValue(Object value) {
        try {
            field.set(classInstance, value);
        } catch(IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the value on the managed {@link PropertyFile}-annotated class'
     * instance,
     * converted <b>from</b> a string.
     */
    public void setStringValue(String value) {
        try {
            setValue(convertToType.invoke(convertersInstance, value));
        } catch(IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
