package com.olexxxxandr.carrepair.domain.validator.model;

import com.olexxxxandr.carrepair.domain.impl.Model;
import com.olexxxxandr.carrepair.domain.validator.ModelValidator;
import com.olexxxxandr.carrepair.domain.validator.util.DomainRequireValidator;
import java.util.List;
import java.util.Map;

class BrandModelValidator extends ModelValidator {

    BrandModelValidator(Map<String, List<String>> validationMessages) {
        super(validationMessages);
    }

    /**
     * A validation process that writes errors to the validationMessages collection of the object's
     * model.
     *
     * @param model current model to validate
     */
    @Override
    public boolean validate(Model model) {
        boolean validateResult = true;
        List<String> messages = DomainRequireValidator.getInstance()
                .getErrorMessages(model.getBrand(), model.getBrand().getId());

        if (!messages.isEmpty()) {
            validationMessages.put("brand", messages);
            validateResult = false;
        }

        if (nextValidator != null) {
            return nextValidator.validate(model);
        }

        return validateResult;
    }
}
