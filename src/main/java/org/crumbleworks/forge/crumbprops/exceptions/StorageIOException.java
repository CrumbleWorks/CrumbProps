package org.crumbleworks.forge.crumbprops.exceptions;

/**
 * Thrown if writing/reading goes wrong.
 * <p>
 * E.g. file is not found.
 * 
 * @author Michael Stocker
 * @since 1.0
 */
public class StorageIOException extends RuntimeException {

    private static final long serialVersionUID = 5319466534210013921L;

    public StorageIOException(String message) {
        super(message);
    }

    public StorageIOException(Throwable cause) {
        super(cause);
    }

    public StorageIOException(String message, Throwable cause) {
        super(message, cause);
    }
}
