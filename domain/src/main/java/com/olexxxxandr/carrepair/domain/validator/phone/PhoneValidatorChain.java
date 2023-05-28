package com.olexxxxandr.carrepair.domain.validator.phone;

import com.olexxxxandr.carrepair.domain.impl.Phone;
import com.olexxxxandr.carrepair.domain.validator.PhoneValidator;
import com.olexxxxandr.carrepair.domain.validator.ValidatorChain;

public class PhoneValidatorChain extends ValidatorChain {

    PhoneValidator firstValidator;

    @Override
    protected void validateChain() {
        firstValidator = new EmployeePhoneValidator(validationMessages);
        firstValidator
                .setNext(new PhoneTypePhoneValidator(validationMessages))
                .setNext(new ValuePhoneValidator(validationMessages));
    }

    public boolean validate(Phone phone) {
        validationMessages.clear();
        firstValidator.validate(phone);
        return validationMessages.size() == 0;
    }

    private static class SingletonHolder {
        public static final PhoneValidatorChain INSTANCE = new PhoneValidatorChain();
    }

    public static PhoneValidatorChain getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
