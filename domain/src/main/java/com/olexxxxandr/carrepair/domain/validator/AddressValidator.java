package com.olexxxxandr.carrepair.domain.validator;

import com.olexxxxandr.carrepair.domain.impl.Address;
import java.util.List;
import java.util.Map;

public abstract class AddressValidator implements Validator<Address, AddressValidator> {

    protected AddressValidator nextValidator;
    protected Map<String, List<String>> validationMessages;

    protected AddressValidator(Map<String, List<String>> validationMessages) {
        this.validationMessages = validationMessages;
    }

    /**
     * Setting the next validation handler
     *
     * @param nextValidator next handler
     */
    @Override
    public AddressValidator setNext(AddressValidator nextValidator) {
        this.nextValidator = nextValidator;
        return this.nextValidator;
    }
}
