package com.olexxxxandr.filter.impl;

import com.olexxxxandr.entity.impl.EmployeeEntity;
import com.olexxxxandr.entity.impl.Money;
import com.olexxxxandr.filter.FilterDto;
import java.time.LocalDateTime;

public record PayrollFilterDto(
        EmployeeEntity employeeEntity,
        String periodType,
        int hourCount,
        Money salary,
        LocalDateTime paymentAt,
        LocalDateTime updatedAt,
        LocalDateTime createdAt)
        implements FilterDto {}
