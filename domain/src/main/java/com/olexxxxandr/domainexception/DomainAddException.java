package com.olexxxxandr.domainexception;

public class DomainAddException extends RuntimeException {

    public DomainAddException() {
        super();
    }

    public DomainAddException(String message) {
        super(message);
    }
}
