package com.olexxxxandr.carrepair.persistence.filter.impl;

import com.olexxxxandr.carrepair.persistence.entity.impl.EmployeeEntity;
import com.olexxxxandr.carrepair.persistence.filter.FilterDto;

public record PhoneFilterDto(EmployeeEntity employeeEntity, String type, String value) implements
        FilterDto {}
