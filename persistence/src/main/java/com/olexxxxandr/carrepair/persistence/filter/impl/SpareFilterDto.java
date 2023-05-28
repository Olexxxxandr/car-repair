package com.olexxxxandr.carrepair.persistence.filter.impl;

import com.olexxxxandr.carrepair.persistence.entity.impl.Money;
import com.olexxxxandr.carrepair.persistence.entity.impl.WorkroomEntity;
import com.olexxxxandr.carrepair.persistence.filter.FilterDto;

public record SpareFilterDto(WorkroomEntity workroomEntity, String name, Money price, Integer quantityInStock)
        implements FilterDto {}
