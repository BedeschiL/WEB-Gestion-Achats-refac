/*
 * Copyright (c) Code écrit par Bedeschi Louis.
 */

package Exceptions;

public class TVANotFoundException extends RuntimeException {
    public TVANotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
