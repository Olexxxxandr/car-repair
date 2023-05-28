package com.olexxxxandr.carrepair.domain.repository;

import com.olexxxxandr.carrepair.domain.impl.User;
import java.util.List;
import java.util.UUID;

public interface UserRepository extends GenericRepository<UUID, User> {

    /**
     * Get the entire collection of entities by filtering in RDMS.
     *
     * @return collection of Domains
     */
    List<User> getAllWhere(String email, String login);
}
