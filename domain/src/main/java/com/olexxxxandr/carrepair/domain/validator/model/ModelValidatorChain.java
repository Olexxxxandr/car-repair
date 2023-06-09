package com.olexxxxandr.carrepair.domain.validator.model;

import com.olexxxxandr.carrepair.domain.impl.Model;
import com.olexxxxandr.carrepair.domain.validator.ModelValidator;
import com.olexxxxandr.carrepair.domain.validator.ValidatorChain;

public class ModelValidatorChain extends ValidatorChain {

    ModelValidator firstValidator;

    @Override
    protected void validateChain() {
        firstValidator = new BrandModelValidator(validationMessages);
        firstValidator.setNext(new NameModelValidator(validationMessages));
    }

    public boolean validate(Model discount) {
        validationMessages.clear();
        firstValidator.validate(discount);
        return validationMessages.size() == 0;
    }

    private static class SingletonHolder {
        public static final ModelValidatorChain INSTANCE = new ModelValidatorChain();
    }

    public static ModelValidatorChain getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
