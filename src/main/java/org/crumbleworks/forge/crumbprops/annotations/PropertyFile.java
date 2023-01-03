package org.crumbleworks.forge.crumbprops.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.crumbleworks.forge.crumbprops.Converters;
import org.crumbleworks.forge.crumbprops.DefaultConvertersImpl;
import org.crumbleworks.forge.crumbprops.DefaultStorageImpl;
import org.crumbleworks.forge.crumbprops.Storage;

/**
 * 
 */

/**
 * Provides metainformation to link this class to a physical file
 * 
 * @author Michael Stocker
 * @since 1.0
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface PropertyFile {

	/**
	 * The name of the physical file.
	 * 
	 * <p>
	 * E.g. <code>mysettings.file</code>
	 */
	String fileName();

	/**
	 * <b>Optional:</b> An absolute or relative path leading to the folder
	 * containing the physical file.
	 * 
	 * <p>
	 * Defaults to the working directory.
	 */
	String fileDir() default ".";

	/**
	 * <b>Optional:</b> The {@link Converters} implementation to be used for
	 * converting values to strings and back.
	 * 
	 * <p>
	 * Defaults to {@link DefaultConvertersImpl}.
	 */
	Class<? extends Converters> converter() default DefaultConvertersImpl.class;

	/**
	 * <b>Optional:</b> The {@link Storage} implementation to be used for writing
	 * and loading the physical file.
	 * 
	 * <p>
	 * Defaults to {@link DefaultStorageImpl}.
	 */
	Class<? extends Storage> storage() default DefaultStorageImpl.class;
}