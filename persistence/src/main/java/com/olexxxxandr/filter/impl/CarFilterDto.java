package com.olexxxxandr.filter.impl;

import com.olexxxxandr.entity.impl.ModelEntity;
import com.olexxxxandr.filter.FilterDto;
import java.awt.Color;
import java.time.LocalDateTime;

public record CarFilterDto(
        ModelEntity modelEntity,
        String number,
        short year,
        String engineType,
        int mileage,
        Color color,
        LocalDateTime updatedAt,
        LocalDateTime createdAt)
        implements FilterDto {}
