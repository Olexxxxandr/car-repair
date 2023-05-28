package com.olexxxxandr.carrepair.domain.validator.brand;

import com.olexxxxandr.carrepair.domain.impl.Brand;
import com.olexxxxandr.carrepair.domain.validator.BrandValidator;
import com.olexxxxandr.carrepair.domain.validator.util.InputValidator;
import java.util.List;
import java.util.Map;

public class NameBrandValidator extends BrandValidator {

    protected NameBrandValidator(Map<String, List<String>> validationMessages) {
        super(validationMessages);
    }

    /**
     * A validation process that writes errors to the validationMessages map collection.
     *
     * @param brand current brand to validate
     */
    @Override
    public boolean validate(Brand brand) {
        boolean validateResult = true;
        List<String> messages = InputValidator.getInstance().getErrorMessages(brand.getName(), 2, 128, true);

        if (!messages.isEmpty()) {
            validationMessages.put("name", messages);
            validateResult = false;
        }

        if (nextValidator != null) {
            return nextValidator.validate(brand);
        }

        return validateResult;
    }
}
