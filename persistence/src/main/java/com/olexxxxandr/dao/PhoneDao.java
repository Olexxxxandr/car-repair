package com.olexxxxandr.dao;

import com.olexxxxandr.entity.impl.PhoneEntity;
import com.olexxxxandr.filter.impl.PhoneFilterDto;
import java.util.UUID;

public interface PhoneDao extends GenericDao<UUID, PhoneEntity, PhoneFilterDto> {}
