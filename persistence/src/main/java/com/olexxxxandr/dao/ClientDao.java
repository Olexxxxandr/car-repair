package com.olexxxxandr.dao;

import com.olexxxxandr.entity.impl.ClientEntity;
import com.olexxxxandr.filter.impl.ClientFilterDto;
import java.sql.Connection;
import java.util.Optional;
import java.util.UUID;

public interface ClientDao extends GenericDao<UUID, ClientEntity, ClientFilterDto> {

    /**
     * Get an entity object by identifier.
     *
     * @param id primary key identifier
     * @param connection JDBC Connection
     * @return Optional<ClientEntity>
     */
    Optional<ClientEntity> findOneById(UUID id, Connection connection);
}
