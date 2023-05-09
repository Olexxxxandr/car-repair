package com.olexxxxandr.domain.validator.client.name;

import com.olexxxxandr.domain.impl.Client;
import com.olexxxxandr.domain.validator.ClientValidateHandler;
import com.olexxxxandr.domain.validator.helper.NameValidator;
import java.util.List;

public class LastNameClientValidateHandler implements ClientValidateHandler {

    private ClientValidateHandler clientValidateHandler;

    /**
     * A validation process that writes errors to the validationMessages collection of the object's
     * client.
     *
     * @param client current client to validate
     */
    @Override
    public void validate(Client client) {
        List<String> errorMessages = NameValidator.getInstance()
                .getErrorMessages(client.getFirstName(), false);
        if(!errorMessages.isEmpty()) {
            client.getValidationMessages().put("lastName", errorMessages);
        }

        clientValidateHandler.validate(client);
    }

    /**
     * Setting the next validation handler
     *
     * @param handler next handler
     */
    @Override
    public void setNext(ClientValidateHandler handler) {
        clientValidateHandler = handler;
    }

    private LastNameClientValidateHandler() {}

    private static class SingletonHandler {
        public static final LastNameClientValidateHandler INSTANCE =
                new LastNameClientValidateHandler();
    }

    public static LastNameClientValidateHandler getInstance() {
        return SingletonHandler.INSTANCE;
    }
}
