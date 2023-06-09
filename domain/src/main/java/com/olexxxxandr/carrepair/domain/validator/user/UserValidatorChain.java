package com.olexxxxandr.carrepair.domain.validator.user;

import com.olexxxxandr.carrepair.domain.impl.User;
import com.olexxxxandr.carrepair.domain.validator.UserValidator;
import com.olexxxxandr.carrepair.domain.validator.ValidatorChain;

public class UserValidatorChain extends ValidatorChain {

    UserValidator firstValidator;

    @Override
    protected void validateChain() {
        firstValidator = new EmailUserValidator(validationMessages);
        firstValidator
                .setNext(new LoginUserValidator(validationMessages))
                .setNext(new PasswordUserValidator(validationMessages));
    }

    public boolean validate(User spare) {
        validationMessages.clear();
        firstValidator.validate(spare);
        return validationMessages.size() == 0;
    }

    private static class SingletonHolder {
        public static final UserValidatorChain INSTANCE = new UserValidatorChain();
    }

    public static UserValidatorChain getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
