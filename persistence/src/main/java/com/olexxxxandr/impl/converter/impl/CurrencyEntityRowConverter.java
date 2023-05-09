package com.olexxxxandr.impl.converter.impl;

import com.olexxxxandr.entity.impl.CurrencyEntity;
import com.olexxxxandr.exception.persistance.NoResultException;
import com.olexxxxandr.impl.converter.EntityRowConverter;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrencyEntityRowConverter implements EntityRowConverter<CurrencyEntity> {

    public static final Logger LOGGER = LoggerFactory.getLogger(CurrencyEntityRowConverter.class);

    @Override
    public CurrencyEntity execute(ResultSet resultSet) {
        try {
            var currencyEntity =
                    CurrencyEntity.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .symbol(resultSet.getString("symbol"))
                            .build();
            LOGGER.info("Результат конвертації ResultSet в об'єктний тип: {}", currencyEntity);
            return currencyEntity;
        } catch (SQLException e) {
            String message =
                    "Не вдалось отримати ResultSet в %s. Детально: %s"
                            .formatted(
                                    AddressEntityRowConverter.class.getSimpleName(),
                                    e.getMessage());
            LOGGER.error(message);
            throw new NoResultException(message);
        }
    }

    private CurrencyEntityRowConverter() {}

    private static class SingletonHolder {
        public static final CurrencyEntityRowConverter INSTANCE = new CurrencyEntityRowConverter();
    }

    public static CurrencyEntityRowConverter getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
