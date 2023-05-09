package com.olexxxxandr.repository;

import com.olexxxxandr.domain.impl.Client;
import com.olexxxxandr.filter.impl.ClientFilterDto;
import java.util.UUID;

public interface ClientRepository extends GenericRepository<UUID, Client, ClientFilterDto> {}
