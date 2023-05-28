package com.olexxxxandr.carrepair.domain.mapper.impl;

import com.olexxxxandr.carrepair.domain.impl.Currency;
import com.olexxxxandr.carrepair.domain.mapper.CurrencyMapper;
import com.olexxxxandr.carrepair.persistence.entity.impl.CurrencyEntity;

public class CurrencyMapperImpl implements CurrencyMapper {

    @Override
    public Currency toDomain(CurrencyEntity entity) {
        var currency = Currency.builder()
                .name(entity.getName())
                .symbol(entity.getSymbol())
                .build();
        currency.setId(entity.getId());
        return currency;
    }

    @Override
    public CurrencyEntity toEntity(Currency domain) {
        return CurrencyEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .symbol(domain.getSymbol())
                .build();
    }

    private CurrencyMapperImpl() {}

    private static class SingletonHolder {
        public static final CurrencyMapperImpl INSTANCE = new CurrencyMapperImpl();
    }

    public static CurrencyMapperImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
