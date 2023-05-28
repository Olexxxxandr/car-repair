package com.olexxxxandr.carrepair.domain.validator.brand;

import com.olexxxxandr.carrepair.domain.impl.Brand;
import com.olexxxxandr.carrepair.domain.validator.BrandValidator;
import com.olexxxxandr.carrepair.domain.validator.ValidatorChain;

public class BrandValidatorChain extends ValidatorChain {

    BrandValidator firstValidator;

    @Override
    protected void validateChain() {
        firstValidator = new NameBrandValidator(validationMessages);
    }

    public boolean validate(Brand brand) {
        validationMessages.clear();
        firstValidator.validate(brand);
        return validationMessages.size() == 0;
    }

    private static class SingletonHolder {
        public static final BrandValidatorChain INSTANCE = new BrandValidatorChain();
    }

    public static BrandValidatorChain getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
