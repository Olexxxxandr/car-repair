package com.olexxxxandr.carrepair.persistence.dao;

import com.olexxxxandr.carrepair.persistence.entity.impl.BrandEntity;
import com.olexxxxandr.carrepair.persistence.filter.impl.BrandFilterDto;
import java.sql.Connection;
import java.util.Optional;

public interface BrandDao extends GenericDao<Integer, BrandEntity, BrandFilterDto> {

    /**
     * Get an entity object by identifier.
     *
     * @param id primary key identifier
     * @param connection JDBC Connection
     * @return Optional<BrandEntity>
     */
    Optional<BrandEntity> findOneById(Integer id, Connection connection);
}
