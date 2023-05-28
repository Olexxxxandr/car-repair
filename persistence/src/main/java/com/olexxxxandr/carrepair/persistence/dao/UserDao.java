package com.olexxxxandr.carrepair.persistence.dao;

import com.olexxxxandr.carrepair.persistence.entity.impl.UserEntity;
import com.olexxxxandr.carrepair.persistence.filter.impl.UserFilterDto;
import java.util.UUID;

public interface UserDao extends GenericDao<UUID, UserEntity, UserFilterDto> {}
