package com.olexxxxandr.carrepair.persistence.dao;

import com.olexxxxandr.carrepair.persistence.entity.impl.DiscountEntity;
import com.olexxxxandr.carrepair.persistence.filter.impl.DiscountFilterDto;
import java.sql.Connection;
import java.util.Optional;

public interface DiscountDao extends GenericDao<Integer, DiscountEntity, DiscountFilterDto> {

    /**
     * Get an entity object by identifier.
     *
     * @param id primary key identifier
     * @param connection JDBC Connection
     * @return Optional<DiscountEntity>
     */
    Optional<DiscountEntity> findOneById(Integer id, Connection connection);
}
