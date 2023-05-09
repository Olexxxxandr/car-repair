package com.olexxxxandr.dao;

import com.olexxxxandr.entity.impl.AddressEntity;
import com.olexxxxandr.filter.impl.AddressFilterDto;
import java.sql.Connection;
import java.util.Optional;
import java.util.UUID;

public interface AddressDao extends GenericDao<UUID, AddressEntity, AddressFilterDto> {

    /**
     * Get an entity object by identifier with connection.
     *
     * @param id primary key identifier
     * @param connection JDBC Connection
     * @return Optional<AddressEntity>
     */
    Optional<AddressEntity> findOneById(UUID id, Connection connection);
}
