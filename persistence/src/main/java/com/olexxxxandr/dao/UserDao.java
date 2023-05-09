package com.olexxxxandr.dao;

import com.olexxxxandr.entity.impl.UserEntity;
import com.olexxxxandr.filter.impl.UserFilterDto;
import java.util.UUID;

public interface UserDao extends GenericDao<UUID, UserEntity, UserFilterDto> {}
