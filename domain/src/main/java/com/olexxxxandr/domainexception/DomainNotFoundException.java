package com.olexxxxandr.domainexception;

/**
 * Thrown by the repository layer when GenericRepository#get(Object) is called and the object no
 * longer exists in the dao layer.
 */
public class DomainNotFoundException extends RuntimeException {
    public DomainNotFoundException() {
        super();
    }

    public DomainNotFoundException(String message) {
        super(message);
    }
}
