package com.olexxxxandr.filter.impl;

import com.olexxxxandr.entity.impl.CarEntity;
import com.olexxxxandr.filter.FilterDto;

public record CarPhotoFilterDto(CarEntity carEntity) implements FilterDto {}
