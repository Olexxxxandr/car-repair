package com.olexxxxandr.filter.impl;

import com.olexxxxandr.entity.impl.AddressEntity;
import com.olexxxxandr.entity.impl.PositionEntity;
import com.olexxxxandr.entity.impl.WorkroomEntity;
import com.olexxxxandr.filter.FilterDto;
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
