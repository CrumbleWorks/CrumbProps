/**
 * 
 */
package org.crumbleworks.forge.crumbprops;

/**
 * Naming scheme is:<BR>
 * convertToTYPE(String value);<BR>
 * convertFromTYPE(TYPE value);<BR>
 * <BR>
 * The underlying parser checks for the 'convertTo' and 'convertFrom' keywords
 * to map the methods for invoking<BR>
 * 
 * @author Michael Stocker
 * @since 1.0
 */
public class DefaultConvertersImpl implements Converters {

    /* SHORT */

    public short convertToShort(String value) {
        return Short.parseShort(value);
    }

    public String convertFromShort(short value) {
        return Integer.toString(value);
    }

    public Short convertToShortWrap(String value) {
        return Short.valueOf(value);
    }

    public String convertFromShortWrap(Short value) {
        return Integer.toString(value);
    }

    /* INTEGER */

    public int convertToInteger(String value) {
        return Integer.parseInt(value);
    }

    public String convertFromInteger(int value) {
        return Integer.toString(value);
    }

    public Integer convertToIntegerWrap(String value) {
        return Integer.valueOf(value);
    }

    public String convertFromIntegerWrap(Integer value) {
        return Integer.toString(value);
    }

    /* LONG */

    public long convertToLong(String value) {
        return Long.parseLong(value);
    }

    public String convertFromLong(long value) {
        return Long.toString(value);
    }

    public Long convertToLongWrap(String value) {
        return Long.valueOf(value);
    }

    public String convertFromLongWrap(Long value) {
        return Long.toString(value);
    }

    /* FLOAT */

    public float convertToFloat(String value) {
        return Float.parseFloat(value);
    }

    public String convertFromFloat(float value) {
        return Float.toString(value);
    }

    public Float convertToFloatWrap(String value) {
        return Float.valueOf(value);
    }

    public String convertFromFloatWrap(Float value) {
        return Float.toString(value);
    }

    /* DOUBLE */

    public double convertToDouble(String value) {
        return Double.parseDouble(value);
    }

    public String convertFromDouble(double value) {
        return Double.toString(value);
    }

    public Double convertToDoubleWrap(String value) {
        return Double.valueOf(value);
    }

    public String convertFromDoubleWrap(Double value) {
        return Double.toString(value);
    }

    /* BOOLEAN */

    public boolean convertToBoolean(String value) {
        return Boolean.parseBoolean(value);
    }

    public String convertFromBoolean(boolean value) {
        return Boolean.toString(value);
    }

    public Boolean convertToBooleanWrap(String value) {
        return Boolean.valueOf(value);
    }

    public String convertFromBooleanWrap(Boolean value) {
        return Boolean.toString(value);
    }

    /* STRING */

    public String convertToString(String value) {
        return value;
    }

    public String convertFromString(String value) {
        return value;
    }
}