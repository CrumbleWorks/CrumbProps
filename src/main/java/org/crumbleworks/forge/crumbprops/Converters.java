/**
 * 
 */
package org.crumbleworks.forge.crumbprops;


/**
 * Naming scheme is:<BR>
 * convertToTYPE(String value);<BR>
 * convertFromTYPE(TYPE value);<BR>
 * <BR>
 * The underlying parser checks for the 'convertToType' and 'convertFromType'
 * methods. It uses the 'convertToType' method to map a new pair of methods for
 * usage.
 * 
 * @see DefaultConvertersImpl
 * @author Michael Stocker
 * @since 1.0
 */
public interface Converters {

    public String convertToString(String value);

    public String convertFromString(String value);
}
