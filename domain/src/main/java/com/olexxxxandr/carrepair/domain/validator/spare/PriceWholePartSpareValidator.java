package com.olexxxxandr.carrepair.domain.validator.spare;

import com.olexxxxandr.carrepair.domain.impl.Spare;
import com.olexxxxandr.carrepair.domain.validator.SpareValidator;
import com.olexxxxandr.carrepair.domain.validator.util.NumberValidator;
import java.util.List;
import java.util.Map;

class PriceWholePartSpareValidator extends SpareValidator {

    protected PriceWholePartSpareValidator(Map<String, List<String>> validationMessages) {
        super(validationMessages);
    }

    /**
     * A validation process that writes errors to the validationMessages map collection.
     *
     * @param spare current spare to validate
     */
    @Override
    public boolean validate(Spare spare) {
        boolean validateResult = true;
        List<String> messages = NumberValidator.getInstance()
                .getErrorMessages(spare.getPrice().wholePart(), 0, Integer.MAX_VALUE, true);

        if (!messages.isEmpty()) {
            validationMessages.put("priceWholePart", messages);
            validateResult = false;
        }

        if (nextValidator != null) {
            return nextValidator.validate(spare);
        }

        return validateResult;
    }
}
