package com.olexxxxandr.dao;

import com.olexxxxandr.entity.impl.PositionEntity;
import com.olexxxxandr.filter.impl.PositionFilterDto;
import java.sql.Connection;
import java.util.Optional;

public interface PositionDao extends GenericDao<Integer, PositionEntity, PositionFilterDto> {

    /**
     * Get an entity object by identifier.
     *
     * @param id primary key identifier
     * @param connection JDBC Connection
     * @return Optional<PositionEntity>
     */
    Optional<PositionEntity> findOneById(Integer id, Connection connection);
}
