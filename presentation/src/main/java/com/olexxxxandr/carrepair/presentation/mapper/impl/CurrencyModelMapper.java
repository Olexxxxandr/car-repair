package com.olexxxxandr.carrepair.presentation.mapper.impl;

import com.olexxxxandr.carrepair.domain.impl.Currency;
import com.olexxxxandr.carrepair.presentation.mapper.ModelMapper;
import com.olexxxxandr.carrepair.presentation.model.impl.CurrencyModel;

public class CurrencyModelMapper implements ModelMapper<Currency, CurrencyModel> {

    @Override
    public CurrencyModel toModel(Currency currency) {
        CurrencyModel currencyModel = CurrencyModel.builder()
                .name(currency.getName())
                .symbol(currency.getSymbol())
                .build();
        currencyModel.setId(currency.getId());
        return currencyModel;
    }

    @Override
    public Currency toDomain(CurrencyModel currencyModel) {
        Currency currency = Currency.builder()
                .name(currencyModel.getName())
                .symbol(currencyModel.getSymbol())
                .build();
        currency.setId(currencyModel.getId());
        return currency;
    }
}
