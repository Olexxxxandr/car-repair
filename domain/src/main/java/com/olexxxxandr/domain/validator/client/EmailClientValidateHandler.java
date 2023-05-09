package com.olexxxxandr.domain.validator.client;

import com.olexxxxandr.domain.impl.Client;
import com.olexxxxandr.domain.validator.ClientValidateHandler;
import com.olexxxxandr.domain.validator.helper.EmailValidator;
import java.util.ArrayList;
import java.util.Objects;

public class EmailClientValidateHandler implements ClientValidateHandler {

    private ClientValidateHandler clientValidateHandler;

    /**
     * A validation process that writes errors to the validationMessages collection of the object's
     * client.
     *
     * @param client current client to validate
     */
    @Override
    public void validate(Client client) {
        String email = client.getEmail();
        ArrayList<String> errorMessages = new ArrayList<>();
        if (Objects.isNull(email)) {
            errorMessages.add("Не може бути порожнім");
        } else if (email.isBlank()) {
            errorMessages.add("Не може бути порожнім");
        } else if (!EmailValidator.getInstance().test(email)) {
            errorMessages.add("Значення не є коректним");
        } else if (email.trim().length() > 256) {
            errorMessages.add("Повинен містити менше 256 символів");
        }
        client.getValidationMessages().put("email", errorMessages);

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

    private EmailClientValidateHandler() {}

    private static class SingletonHandler {
        public static final EmailClientValidateHandler INSTANCE = new EmailClientValidateHandler();
    }

    public static EmailClientValidateHandler getInstance() {
        return SingletonHandler.INSTANCE;
    }
}
