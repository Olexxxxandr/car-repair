package com.olexxxxandr.carrepair.domain.validator.service;

import com.olexxxxandr.carrepair.domain.impl.Service;
import com.olexxxxandr.carrepair.domain.validator.ServiceValidator;
import com.olexxxxandr.carrepair.domain.validator.util.PhotoRequireValidator;
import java.util.List;
import java.util.Map;

class PhotoServiceValidator extends ServiceValidator {

    protected PhotoServiceValidator(Map<String, List<String>> validationMessages) {
        super(validationMessages);
    }

    /**
     * A validation process that writes errors to the validationMessages map collection.
     *
     * @param service current service to validate
     */
    @Override
    public boolean validate(Service service) {
        boolean validateResult = true;
        List<String> messages = PhotoRequireValidator.getInstance().getErrorMessages(service.getPhoto());

        if (!messages.isEmpty()) {
            validationMessages.put("photo", messages);
            validateResult = false;
        }

        if (nextValidator != null) {
            return nextValidator.validate(service);
        }

        return validateResult;
    }
}
