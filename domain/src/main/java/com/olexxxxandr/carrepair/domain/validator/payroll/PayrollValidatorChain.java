package com.olexxxxandr.carrepair.domain.validator.payroll;

import com.olexxxxandr.carrepair.domain.impl.Payroll;
import com.olexxxxandr.carrepair.domain.validator.PayrollValidator;
import com.olexxxxandr.carrepair.domain.validator.ValidatorChain;

public class PayrollValidatorChain extends ValidatorChain {

    PayrollValidator firstValidator;

    @Override
    protected void validateChain() {
        firstValidator = new EmployeePayrollValidator(validationMessages);
        firstValidator
                .setNext(new PeriodTypePayrollValidator(validationMessages))
                .setNext(new HourCountPayrollValidator(validationMessages))
                .setNext(new SalaryWholePartPayrollValidator(validationMessages))
                .setNext(new SalaryDecimalPartPayrollValidator(validationMessages))
                .setNext(new PaymentsAtPayrollValidator(validationMessages));
    }

    public boolean validate(Payroll payroll) {
        validationMessages.clear();
        firstValidator.validate(payroll);
        return validationMessages.size() == 0;
    }

    private static class SingletonHolder {
        public static final PayrollValidatorChain INSTANCE = new PayrollValidatorChain();
    }

    public static PayrollValidatorChain getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
