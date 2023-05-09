package com.olexxxxandr.domain.validator.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NameValidator {

    public List<String> getErrorMessages(String name, boolean isRequired) {
        List<String> validationMessages = new ArrayList<>();
        if (Objects.isNull(name) && isRequired) {
            validationMessages.add("Не може бути порожнім");
        } else if(Objects.nonNull(name)) {
            if (name.isBlank() && isRequired) {
                validationMessages.add("Не може бути порожнім");
            } else if (name.trim().length() < 2 && isRequired) {
                validationMessages.add("Повинен містити більше 1 символа");
            } else if (!name.matches("[А-ЩЬЮЯҐЄІЇа-щьюяґєії']+")) {
                validationMessages.add("Лише українські символи та символ апострофу");
            } else if (name.trim().length() > 256) {
                validationMessages.add("Повинен містити менше 256 символів");
            }
        }


        return validationMessages;
    }

    private NameValidator() {}

    private static class SingletonHandler {
        public static final NameValidator INSTANCE = new NameValidator();
    }

    public static NameValidator getInstance() {
        return SingletonHandler.INSTANCE;
    }
}
