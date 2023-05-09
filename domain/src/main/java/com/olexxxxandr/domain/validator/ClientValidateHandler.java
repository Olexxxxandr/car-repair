package com.olexxxxandr.domain.validator;

import com.olexxxxandr.domain.impl.Client;

public interface ClientValidateHandler {

    /**
     * A validation process that writes errors to the validationMessages collection of the object's
     * client.
     *
     * @param client current client to validate
     */
    void validate(Client client);

    /**
     * Setting the next validation handler
     *
     * @param handler next handler
     */
    void setNext(ClientValidateHandler handler);
}
