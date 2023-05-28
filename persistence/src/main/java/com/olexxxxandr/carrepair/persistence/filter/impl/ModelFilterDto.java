package com.olexxxxandr.carrepair.persistence.filter.impl;

import com.olexxxxandr.carrepair.persistence.entity.impl.BrandEntity;
import com.olexxxxandr.carrepair.persistence.filter.FilterDto;

public record ModelFilterDto(BrandEntity brandEntity, String name) implements FilterDto {}
