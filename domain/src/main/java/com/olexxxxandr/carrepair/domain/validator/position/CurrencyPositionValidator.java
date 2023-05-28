package com.olexxxxandr.carrepair.domain.validator.position;

import com.olexxxxandr.carrepair.domain.impl.Position;
import com.olexxxxandr.carrepair.domain.validator.PositionValidator;
import com.olexxxxandr.carrepair.domain.validator.util.DomainRequireValidator;
import java.util.List;
import java.util.Map;

class CurrencyPositionValidator extends PositionValidator {

    protected CurrencyPositionValidator(Map<String, List<String>> validationMessages) {
        super(validationMessages);
    }

    /**
     * A validation process that writes errors to the validationMessages map collection.
     *
     * @param position current position to validate
     */
    @Override
    public boolean validate(Position position) {
        boolean validateResult = true;
        List<String> messages = DomainRequireValidator.getInstance()
                .getErrorMessages(position.getCurrency(), position.getCurrency().getId());

        if (!messages.isEmpty()) {
            validationMessages.put("currency", messages);
            validateResult = false;
        }

        if (nextValidator != null) {
            return nextValidator.validate(position);
        }

        return validateResult;
    }
}
