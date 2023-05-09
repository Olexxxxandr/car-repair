package com.olexxxxandr.impl.converter.impl;

import com.olexxxxandr.entity.impl.CarPhotoEntity;
import com.olexxxxandr.exception.persistance.EntityNotFoundException;
import com.olexxxxandr.exception.persistance.NoResultException;
import com.olexxxxandr.factory.DaoFactory;
import com.olexxxxandr.impl.converter.EntityRowConverter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarPhotoEntityRowConverter implements EntityRowConverter<CarPhotoEntity> {

    public static final Logger LOGGER = LoggerFactory.getLogger(CarPhotoEntityRowConverter.class);

    @Override
    public CarPhotoEntity execute(ResultSet resultSet) {
        try {
            var carEntity =
                    DaoFactory.getInstance()
                            .getCarDao()
                            .findOneById(UUID.fromString(resultSet.getString("car_id")))
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "Не вдалось отримати об'єкт сутності"
                                                            + " CarEntity, так як вона відсутня в"
                                                            + " базі даних."));
            var phoneEntity =
                    CarPhotoEntity.builder()
                            .id(UUID.fromString(resultSet.getString("id")))
                            .carEntity(carEntity)
                            .photo(resultSet.getBytes("photo"))
                            .build();
            phoneEntity.setDescription(resultSet.getObject("description", String.class));

            LOGGER.info("Результат конвертації ResultSet в об'єктний тип: {}", phoneEntity);
            return phoneEntity;
        } catch (SQLException e) {
            String message =
                    "Не вдалось отримати ResultSet в %s. Детально: %s"
                            .formatted(
                                    CarPhotoEntityRowConverter.class.getSimpleName(),
                                    e.getMessage());
            LOGGER.error(message);
            throw new NoResultException(message);
        }
    }

    private CarPhotoEntityRowConverter() {}

    private static class SingletonHolder {
        public static final CarPhotoEntityRowConverter INSTANCE = new CarPhotoEntityRowConverter();
    }

    public static CarPhotoEntityRowConverter getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
