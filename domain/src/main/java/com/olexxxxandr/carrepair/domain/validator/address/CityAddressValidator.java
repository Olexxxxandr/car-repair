package com.olexxxxandr.carrepair.domain.validator.address;

import com.olexxxxandr.carrepair.domain.impl.Address;
import com.olexxxxandr.carrepair.domain.validator.AddressValidator;
import com.olexxxxandr.carrepair.domain.validator.util.InputValidator;
import java.util.List;
import java.util.Map;

class CityAddressValidator extends AddressValidator {

    protected CityAddressValidator(Map<String, List<String>> validationMessages) {
        super(validationMessages);
    }

    /**
     * A validation process that writes errors to the validationMessages map collection.
     *
     * @param address current address to validate
     */
    @Override
    public boolean validate(Address address) {
        boolean validateResult = true;
        List<String> messages = InputValidator.getInstance().getErrorMessages(address.getCity(), 2, 64, true);

        if (!messages.isEmpty()) {
            validationMessages.put("city", messages);
            validateResult = false;
        }

        if (nextValidator != null) {
            return nextValidator.validate(address);
        }

        return validateResult;
    }
}
