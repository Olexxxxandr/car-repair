package com.olexxxxandr.filter.impl;

import com.olexxxxandr.entity.impl.CurrencyEntity;
import com.olexxxxandr.entity.impl.Money;
import com.olexxxxandr.filter.FilterDto;

public record ServiceFilterDto(String name, CurrencyEntity currencyEntity, Money price)
        implements FilterDto {}
