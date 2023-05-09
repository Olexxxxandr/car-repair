package com.olexxxxandr.impl.converter.impl;

import com.olexxxxandr.entity.impl.UserEntity;
import com.olexxxxandr.exception.persistance.NoResultException;
import com.olexxxxandr.impl.converter.EntityRowConverter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserEntityRowConverter implements EntityRowConverter<UserEntity> {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserEntityRowConverter.class);

    @Override
    public UserEntity execute(ResultSet resultSet) {
        try {
            var userEntity =
                    UserEntity.builder()
                            .id(UUID.fromString(resultSet.getString("employee_id")))
                            .email(resultSet.getString("email"))
                            .login(resultSet.getString("login"))
                            .password(resultSet.getString("password"))
                            .build();
            LOGGER.info("Результат конвертації ResultSet в об'єктний тип: {}", userEntity);
            return userEntity;
        } catch (SQLException e) {
            String message =
                    "Не вдалось отримати ResultSet в %s. Детально: %s"
                            .formatted(
                                    UserEntityRowConverter.class.getSimpleName(), e.getMessage());
            LOGGER.error(message);
            throw new NoResultException(message);
        }
    }

    private UserEntityRowConverter() {}

    private static class SingletonHolder {
        public static final UserEntityRowConverter INSTANCE = new UserEntityRowConverter();
    }

    public static UserEntityRowConverter getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
