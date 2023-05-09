package com.olexxxxandr.dao;

import com.olexxxxandr.entity.impl.CarEntity;
import com.olexxxxandr.filter.impl.CarFilterDto;
import java.sql.Connection;
import java.util.Optional;
import java.util.UUID;

public interface CarDao extends GenericDao<UUID, CarEntity, CarFilterDto> {

    /**
     * Get an entity object by identifier with connection.
     *
     * @param id primary key identifier
     * @param connection JDBC Connection
     * @return Optional<CarEntity>
     */
    Optional<CarEntity> findOneById(UUID id, Connection connection);
}
