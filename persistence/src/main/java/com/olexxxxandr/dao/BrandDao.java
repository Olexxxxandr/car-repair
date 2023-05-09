package com.olexxxxandr.dao;

import com.olexxxxandr.entity.impl.BrandEntity;
import com.olexxxxandr.filter.impl.BrandFilterDto;
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
