package com.olexxxxandr.carrepair.persistence.filter.impl;

import com.olexxxxandr.carrepair.persistence.entity.impl.AddressEntity;
import com.olexxxxandr.carrepair.persistence.entity.impl.PositionEntity;
import com.olexxxxandr.carrepair.persistence.entity.impl.WorkroomEntity;
import com.olexxxxandr.carrepair.persistence.filter.FilterDto;
import java.time.LocalDateTime;

public record EmployeeFilterDto(
        AddressEntity addressEntity,
        WorkroomEntity workroomEntity,
        PositionEntity positionEntity,
        String firstName,
        String lastName,
        String middleName,
        LocalDateTime updatedAt,
        LocalDateTime createdAt)
        implements FilterDto {}
