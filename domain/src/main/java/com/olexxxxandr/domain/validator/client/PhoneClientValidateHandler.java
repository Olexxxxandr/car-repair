package com.olexxxxandr.domain.validator.client;

import com.olexxxxandr.domain.impl.Client;
import com.olexxxxandr.domain.validator.ClientValidateHandler;
import java.util.ArrayList;
import java.util.Objects;

public class PhoneClientValidateHandler implements ClientValidateHandler {

    private ClientValidateHandler clientValidateHandler;

    /**
     * A validation process that writes errors to the validationMessages collection of the object's
     * client.
     *
     * @param client current client to validate
     */
    @Override
    public void validate(Client client) {
        String phone = client.getPhone();
        ArrayList<String> errorMessages = new ArrayList<>();
        if (Objects.isNull(phone)) {
            errorMessages.add("Не може бути порожнім");
        } else if (phone.isBlank()) {
            errorMessages.add("Не може бути порожнім");
        } else if (!phone.matches("\\d+")) {
            errorMessages.add("Лише цифри");
        } else if (phone.trim().length() < 7) {
            errorMessages.add("Повинен містити більше 6 цифр");
        } else if (phone.trim().length() > 16) {
            errorMessages.add("Повинен містити менше 15 цифр");
        }
        client.getValidationMessages().put("phone", errorMessages);

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

    private PhoneClientValidateHandler() {}

    private static class SingletonHandler {
        public static final PhoneClientValidateHandler INSTANCE = new PhoneClientValidateHandler();
    }

    public static PhoneClientValidateHandler getInstance() {
        return SingletonHandler.INSTANCE;
    }
}
