package com.olexxxxandr.filter.impl;

import com.olexxxxandr.entity.impl.CarEntity;
import com.olexxxxandr.entity.impl.ClientEntity;
import com.olexxxxandr.entity.impl.DiscountEntity;
import com.olexxxxandr.entity.impl.Money;
import com.olexxxxandr.filter.FilterDto;
import java.time.LocalDateTime;

public record OrderFilterDto(
        ClientEntity clientEntity,
        CarEntity carEntity,
        DiscountEntity discountEntity,
        Money price,
        String paymentType,
        LocalDateTime paymentAt,
        LocalDateTime updatedAt,
        LocalDateTime createdAt)
        implements FilterDto {}
