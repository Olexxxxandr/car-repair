package com.olexxxxandr.carrepair.domain.repository;

import com.olexxxxandr.carrepair.domain.impl.Discount;
import java.util.List;

public interface DiscountRepository extends GenericRepository<Integer, Discount> {

    /**
     * Get the entire collection of entities by filtering in RDMS.
     *
     * @return collection of Domains
     */
    List<Discount> getAllWhere(String name, Short value);
}
