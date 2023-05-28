package com.olexxxxandr.carrepair.domain.repository;

import com.olexxxxandr.carrepair.domain.impl.Brand;
import java.util.List;

public interface BrandRepository extends GenericRepository<Integer, Brand> {

    /**
     * Get the entire collection of entities by filtering in RDMS.
     *
     * @return collection of Domains
     */
    List<Brand> getAllWhere(String name);
}
