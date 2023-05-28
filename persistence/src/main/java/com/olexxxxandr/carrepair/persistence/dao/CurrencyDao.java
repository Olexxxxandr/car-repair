package com.olexxxxandr.carrepair.persistence.dao;

import com.olexxxxandr.carrepair.persistence.entity.impl.CurrencyEntity;
import com.olexxxxandr.carrepair.persistence.filter.impl.CurrencyFilterDto;
import java.sql.Connection;
import java.util.Optional;

public interface CurrencyDao extends GenericDao<Integer, CurrencyEntity, CurrencyFilterDto> {

    /**
     * Get an entity object by identifier.
     *
     * @param id primary key identifier
     * @param connection JDBC Connection
     * @return Optional<EmployeeEntity>
     */
    Optional<CurrencyEntity> findOneById(Integer id, Connection connection);
}
