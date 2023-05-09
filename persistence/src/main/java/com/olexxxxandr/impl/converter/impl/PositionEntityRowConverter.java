package com.olexxxxandr.impl.converter.impl;

import com.olexxxxandr.entity.impl.CurrencyEntity;
import com.olexxxxandr.entity.impl.Money;
import com.olexxxxandr.entity.impl.PositionEntity;
import com.olexxxxandr.exception.persistance.EntityNotFoundException;
import com.olexxxxandr.exception.persistance.NoResultException;
import com.olexxxxandr.factory.DaoFactory;
import com.olexxxxandr.impl.converter.EntityRowConverter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PositionEntityRowConverter implements EntityRowConverter<PositionEntity> {

    public static final Logger LOGGER = LoggerFactory.getLogger(PositionEntityRowConverter.class);

    @Override
    public PositionEntity execute(ResultSet resultSet) {
        try {
            Connection connection = resultSet.getStatement().getConnection();

            CurrencyEntity currencyEntity =
                    DaoFactory.getInstance()
                            .getCurrencyDao()
                            .findOneById(resultSet.getInt("currency_id"), connection)
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "Не вдалось отримати об'єкт сутності"
                                                        + " CurrencyEntity, так як вона відсутня в"
                                                        + " базі даних."));
            var positionEntity =
                    PositionEntity.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .currencyEntity(currencyEntity)
                            .salaryPerHour(
                                    new Money(
                                            resultSet.getInt("salary_per_hour_whole_part"),
                                            resultSet.getInt("salary_per_hour_whole_part")))
                            .build();
            positionEntity.setDescription(resultSet.getObject("description", String.class));
            LOGGER.info("Результат конвертації ResultSet в об'єктний тип: {}", positionEntity);
            return positionEntity;
        } catch (SQLException e) {
            String message =
                    "Не вдалось отримати ResultSet в %s. Детально: %s"
                            .formatted(
                                    WorkroomEntityRowConverter.class.getSimpleName(),
                                    e.getMessage());
            LOGGER.error(message);
            throw new NoResultException(message);
        }
    }

    private PositionEntityRowConverter() {}

    private static class SingletonHolder {
        public static final PositionEntityRowConverter INSTANCE = new PositionEntityRowConverter();
    }

    public static PositionEntityRowConverter getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
