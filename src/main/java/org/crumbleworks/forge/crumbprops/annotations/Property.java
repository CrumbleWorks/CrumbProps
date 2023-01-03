package org.crumbleworks.forge.crumbprops.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Links a field to a property in the file
 * 
 * @author Michael Stocker
 * @since 1.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface Property {

    /**
     * The name of the property in the file.
     */
    String key();

    /**
     * <b>Optional:</b> A default value for the property if it isn't set.
     * <p>
     * Empty strings are ignored / not written when saving.
     */
    String defaultValue() default "";
}