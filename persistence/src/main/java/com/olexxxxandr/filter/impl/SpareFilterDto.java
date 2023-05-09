package com.olexxxxandr.filter.impl;

import com.olexxxxandr.entity.impl.Money;
import com.olexxxxandr.entity.impl.WorkroomEntity;
import com.olexxxxandr.filter.FilterDto;

public record SpareFilterDto(
        WorkroomEntity workroomEntity, String name, Money price, int quantityInStock)
        implements FilterDto {}
