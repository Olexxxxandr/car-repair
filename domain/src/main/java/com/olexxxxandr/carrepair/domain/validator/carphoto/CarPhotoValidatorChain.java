package com.olexxxxandr.carrepair.domain.validator.carphoto;

import com.olexxxxandr.carrepair.domain.impl.CarPhoto;
import com.olexxxxandr.carrepair.domain.validator.CarPhotoValidator;
import com.olexxxxandr.carrepair.domain.validator.ValidatorChain;

public class CarPhotoValidatorChain extends ValidatorChain {

    CarPhotoValidator firstValidator;

    @Override
    protected void validateChain() {
        firstValidator = new CarCarPhotoValidator(validationMessages);
        firstValidator
                .setNext(new PhotoCarPhotoValidator(validationMessages))
                .setNext(new DescriptionCarPhotoValidator(validationMessages));
    }

    public boolean validate(CarPhoto position) {
        validationMessages.clear();
        firstValidator.validate(position);
        return validationMessages.size() == 0;
    }

    private static class SingletonHolder {
        public static final CarPhotoValidatorChain INSTANCE = new CarPhotoValidatorChain();
    }

    public static CarPhotoValidatorChain getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
