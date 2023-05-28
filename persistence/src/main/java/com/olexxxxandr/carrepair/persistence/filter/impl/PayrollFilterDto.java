package com.olexxxxandr.carrepair.persistence.filter.impl;

import com.olexxxxandr.carrepair.persistence.entity.impl.EmployeeEntity;
import com.olexxxxandr.carrepair.persistence.entity.impl.Money;
import com.olexxxxandr.carrepair.persistence.filter.FilterDto;
import java.time.LocalDateTime;

public record PayrollFilterDto(
        EmployeeEntity employeeEntity,
        String periodType,
        Integer hourCount,
        Money salary,
        LocalDateTime paymentAt,
        LocalDateTime updatedAt,
        LocalDateTime createdAt)
        implements FilterDto {}
