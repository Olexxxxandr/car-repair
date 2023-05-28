package com.olexxxxandr.carrepair.domain.repository;

import com.olexxxxandr.carrepair.domain.impl.Employee;
import com.olexxxxandr.carrepair.domain.impl.Phone;
import java.util.List;
import java.util.UUID;

public interface PhoneRepository extends GenericRepository<UUID, Phone> {

    /**
     * Get the entire collection of entities by filtering in RDMS.
     *
     * @return collection of Domains
     */
    List<Phone> getAllWhere(Employee employee, String type, String value);
}
