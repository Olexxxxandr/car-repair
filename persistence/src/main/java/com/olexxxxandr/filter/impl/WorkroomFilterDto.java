package com.olexxxxandr.filter.impl;

import com.olexxxxandr.entity.impl.AddressEntity;
import com.olexxxxandr.filter.FilterDto;

public record WorkroomFilterDto(AddressEntity addressEntity, String name) implements FilterDto {}
