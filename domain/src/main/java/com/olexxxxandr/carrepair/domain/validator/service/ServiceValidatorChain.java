package com.olexxxxandr.carrepair.domain.validator.service;

import com.olexxxxandr.carrepair.domain.impl.Service;
import com.olexxxxandr.carrepair.domain.validator.ServiceValidator;
import com.olexxxxandr.carrepair.domain.validator.ValidatorChain;

public class ServiceValidatorChain extends ValidatorChain {

    ServiceValidator firstValidator;

    @Override
    protected void validateChain() {
        firstValidator = new NameServiceValidator(validationMessages);
        firstValidator
                .setNext(new DescriptionServiceValidator(validationMessages))
                .setNext(new PhotoServiceValidator(validationMessages))
                .setNext(new CurrencyServiceValidator(validationMessages))
                .setNext(new PriceWholePartServiceValidator(validationMessages))
                .setNext(new PriceDecimalPartServiceValidator(validationMessages));
    }

    public boolean validate(Service position) {
        validationMessages.clear();
        firstValidator.validate(position);
        return validationMessages.size() == 0;
    }

    private static class SingletonHolder {
        public static final ServiceValidatorChain INSTANCE = new ServiceValidatorChain();
    }

    public static ServiceValidatorChain getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
