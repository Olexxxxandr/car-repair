package com.olexxxxandr.carrepair.persistence.dao;

import com.olexxxxandr.carrepair.persistence.entity.impl.ModelEntity;
import com.olexxxxandr.carrepair.persistence.filter.impl.ModelFilterDto;
import java.sql.Connection;
import java.util.Optional;

public interface ModelDao extends GenericDao<Integer, ModelEntity, ModelFilterDto> {

    /**
     * Get an entity object by identifier with connection.
     *
     * @param id primary key identifier
     * @param connection JDBC Connection
     * @return Optional<ModelEntity>
     */
    Optional<ModelEntity> findOneById(Integer id, Connection connection);
}
