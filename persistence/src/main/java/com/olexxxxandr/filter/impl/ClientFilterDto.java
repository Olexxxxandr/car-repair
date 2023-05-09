package com.olexxxxandr.filter.impl;

import com.olexxxxandr.filter.FilterDto;
import java.time.LocalDateTime;

public record ClientFilterDto(
        String phone,
        String email,
        String firstName,
        String lastName,
        String middleName,
        LocalDateTime updatedAt,
        LocalDateTime createdAt)
        implements FilterDto {}
