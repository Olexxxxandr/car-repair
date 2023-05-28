package com.olexxxxandr.carrepair.domain.validator.discount;

import com.olexxxxandr.carrepair.domain.impl.Discount;
import com.olexxxxandr.carrepair.domain.validator.DiscountValidator;
import com.olexxxxandr.carrepair.domain.validator.ValidatorChain;

public class DiscountValidatorChain extends ValidatorChain {

    DiscountValidator firstValidator;

    @Override
    protected void validateChain() {
        firstValidator = new NameDiscountValidator(validationMessages);
        firstValidator
                .setNext(new DescriptionDiscountValidator(validationMessages))
                .setNext(new ValueDiscountValidator(validationMessages));
    }

    public boolean validate(Discount discount) {
        validationMessages.clear();
        firstValidator.validate(discount);
        return validationMessages.size() == 0;
    }

    private static class SingletonHolder {
        public static final DiscountValidatorChain INSTANCE = new DiscountValidatorChain();
    }

    public static DiscountValidatorChain getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
