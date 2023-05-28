package com.olexxxxandr.carrepair.persistence.filter.impl;

import com.olexxxxandr.carrepair.persistence.entity.impl.CarEntity;
import com.olexxxxandr.carrepair.persistence.filter.FilterDto;

public record CarPhotoFilterDto(CarEntity carEntity) implements FilterDto {}
