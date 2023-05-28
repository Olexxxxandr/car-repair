package com.olexxxxandr.carrepair.domain.repository;

import com.olexxxxandr.carrepair.domain.impl.Address;
import com.olexxxxandr.carrepair.domain.impl.Workroom;
import java.util.List;
import java.util.UUID;

public interface WorkroomRepository extends GenericRepository<UUID, Workroom> {

    /**
     * Get the entire collection of entities by filtering in RDMS.
     *
     * @return collection of Domains
     */
    List<Workroom> getAllWhere(Address address, String name);
}
