package com.olexxxxandr.dao;

import com.olexxxxandr.entity.impl.CarPhotoEntity;
import com.olexxxxandr.filter.impl.CarPhotoFilterDto;
import java.util.UUID;

public interface CarPhotoDao extends GenericDao<UUID, CarPhotoEntity, CarPhotoFilterDto> {}
