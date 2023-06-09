package com.olexxxxandr.carrepair.domain.repository;

import com.olexxxxandr.carrepair.domain.impl.Currency;
import java.util.List;

public interface CurrencyRepository extends GenericRepository<Integer, Currency> {

    /**
     * Get the entire collection of entities by filtering in RDMS.
     *
     * @return collection of Domains
     */
    List<Currency> getAllWhere(String name, String symbol);
}
