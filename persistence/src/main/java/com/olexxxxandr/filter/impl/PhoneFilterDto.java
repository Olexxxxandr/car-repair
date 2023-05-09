package com.olexxxxandr.filter.impl;

import com.olexxxxandr.entity.impl.EmployeeEntity;
import com.olexxxxandr.filter.FilterDto;

public record PhoneFilterDto(EmployeeEntity employeeEntity, String type, String value)
        implements FilterDto {}
