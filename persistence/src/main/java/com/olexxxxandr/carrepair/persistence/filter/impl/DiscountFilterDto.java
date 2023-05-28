package com.olexxxxandr.carrepair.persistence.filter.impl;

import com.olexxxxandr.carrepair.persistence.filter.FilterDto;

public record DiscountFilterDto(String name, short value) implements FilterDto {}
