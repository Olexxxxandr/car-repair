package com.olexxxxandr.carrepair.persistence.filter.impl;

import com.olexxxxandr.carrepair.persistence.entity.impl.CurrencyEntity;
import com.olexxxxandr.carrepair.persistence.entity.impl.Money;
import com.olexxxxandr.carrepair.persistence.filter.FilterDto;

public record ServiceFilterDto(String name, CurrencyEntity currencyEntity, Money price) implements
        FilterDto {}
