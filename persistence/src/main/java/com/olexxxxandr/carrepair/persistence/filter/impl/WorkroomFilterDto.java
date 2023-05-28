package com.olexxxxandr.carrepair.persistence.filter.impl;

import com.olexxxxandr.carrepair.persistence.entity.impl.AddressEntity;
import com.olexxxxandr.carrepair.persistence.filter.FilterDto;

public record WorkroomFilterDto(AddressEntity addressEntity, String name) implements FilterDto {}
