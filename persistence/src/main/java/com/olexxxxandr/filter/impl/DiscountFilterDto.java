package com.olexxxxandr.filter.impl;

import com.olexxxxandr.filter.FilterDto;

public record DiscountFilterDto(String name, short value) implements FilterDto {}
