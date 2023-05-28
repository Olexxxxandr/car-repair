package com.olexxxxandr.carrepair.persistence.filter.impl;

import com.olexxxxandr.carrepair.persistence.filter.FilterDto;

public record CurrencyFilterDto(String name, String symbol) implements FilterDto {}
