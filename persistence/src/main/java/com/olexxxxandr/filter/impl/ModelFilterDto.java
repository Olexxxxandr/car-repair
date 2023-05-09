package com.olexxxxandr.filter.impl;

import com.olexxxxandr.entity.impl.BrandEntity;
import com.olexxxxandr.filter.FilterDto;

public record ModelFilterDto(BrandEntity brandEntity, String name) implements FilterDto {}
