package com.olexxxxandr.carrepair.persistence.dao;

import com.olexxxxandr.carrepair.persistence.entity.impl.PhoneEntity;
import com.olexxxxandr.carrepair.persistence.filter.impl.PhoneFilterDto;
import java.util.UUID;

public interface PhoneDao extends GenericDao<UUID, PhoneEntity, PhoneFilterDto> {}
