package com.olexxxxandr.carrepair.domain.validator.role;

import com.olexxxxandr.carrepair.domain.impl.Role;
import com.olexxxxandr.carrepair.domain.validator.RoleValidator;
import com.olexxxxandr.carrepair.domain.validator.ValidatorChain;

public class RoleValidatorChain extends ValidatorChain {

    RoleValidator firstValidator;

    @Override
    protected void validateChain() {
        firstValidator = new CanEditRoleValidator(validationMessages, "users");
        firstValidator
                .setNext(new CanEditRoleValidator(validationMessages, "spares"))
                .setNext(new CanEditRoleValidator(validationMessages, "clients"))
                .setNext(new CanEditRoleValidator(validationMessages, "services"))
                .setNext(new CanEditRoleValidator(validationMessages, "orders"))
                .setNext(new CanEditRoleValidator(validationMessages, "payrolls"));
    }

    public boolean validate(Role role) {
        validationMessages.clear();
        firstValidator.validate(role);
        return validationMessages.size() == 0;
    }

    private static class SingletonHolder {
        public static final RoleValidatorChain INSTANCE = new RoleValidatorChain();
    }

    public static RoleValidatorChain getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
