package org.crumbleworks.forge.crumbprops.exceptions;

/**
 * Thrown if the object to be stored/loaded is not managed by the respective
 * manager.
 * 
 * @author Michael Stocker
 * @since 1.0
 */
public class ObjectNotManagedException extends RuntimeException {

    private static final long serialVersionUID = -8380365683450802141L;

    public ObjectNotManagedException(Object object) {
        super("Object " + object
                + " unmanaged. You need to 'add(..)' it to this manager first!");
    };
}
