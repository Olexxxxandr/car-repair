package com.olexxxxandr.dao;

import com.olexxxxandr.entity.impl.WorkroomEntity;
import com.olexxxxandr.filter.impl.WorkroomFilterDto;
import java.sql.Connection;
import java.util.Optional;
import java.util.UUID;

public interface WorkroomDao extends GenericDao<UUID, WorkroomEntity, WorkroomFilterDto> {

    /**
     * Get an entity object by identifier.
     *
     * @param id primary key identifier
     * @param connection JDBC Connection
     * @return Optional<WorkroomEntity>
     */
    Optional<WorkroomEntity> findOneById(UUID id, Connection connection);
}
