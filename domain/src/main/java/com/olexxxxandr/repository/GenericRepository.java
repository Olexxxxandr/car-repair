package com.olexxxxandr.repository;

import com.olexxxxandr.domain.Domain;
import com.olexxxxandr.filter.FilterDto;
import java.util.List;

public interface GenericRepository<K, D extends Domain, F extends FilterDto> {

    /**
     * Get a domain object by identifier.
     *
     * @param id primary key identifier
     * @return D domain object
     */
    D get(K id);

    /**
     * Get the entire collection of entities.
     *
     * @return collection of Entities
     */
    List<D> getAll();

    /**
     * Get the entire collection of entities by filtering in RDMS.
     *
     * @param filter values for where conditions
     * @return collection of Entities
     */
    List<D> getAll(F filter);

    /**
     * Save the entity to the database table.
     *
     * @param domain persistent entity
     * @return entity, with identifier of the last added record from the database
     */
    D add(D domain);

    /**
     * Update the domain to the database table.
     *
     * @param domain persistent entity
     * @return entity, with identifier of the last added record from the database
     */
    D set(K id, D domain);

    /**
     * Delete domain object from collection.
     *
     * @param id primary key identifier
     */
    void remove(K id);
}
