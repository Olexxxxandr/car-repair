package com.olexxxxandr.carrepair.domain.validator.payroll;

import com.olexxxxandr.carrepair.domain.impl.Payroll;
import com.olexxxxandr.carrepair.domain.validator.PayrollValidator;
import com.olexxxxandr.carrepair.domain.validator.util.NumberValidator;
import java.util.List;
import java.util.Map;

class SalaryWholePartPayrollValidator extends PayrollValidator {

    protected SalaryWholePartPayrollValidator(Map<String, List<String>> validationMessages) {
        super(validationMessages);
    }

    /**
     * A validation process that writes errors to the validationMessages map collection.
     *
     * @param payroll current payroll to validate
     */
    @Override
    public boolean validate(Payroll payroll) {
        boolean validateResult = true;
        List<String> messages = NumberValidator.getInstance()
                .getErrorMessages(payroll.getSalary().wholePart(), 0, Integer.MAX_VALUE, true);

        if (!messages.isEmpty()) {
            validationMessages.put("salaryWholePart", messages);
            validateResult = false;
        }

        if (nextValidator != null) {
            return nextValidator.validate(payroll);
        }

        return validateResult;
    }
}
