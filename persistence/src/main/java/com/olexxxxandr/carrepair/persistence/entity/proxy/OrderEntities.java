package com.olexxxxandr.carrepair.persistence.entity.proxy;

import com.olexxxxandr.carrepair.persistence.entity.impl.OrderEntity;
import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface OrderEntities {
    List<OrderEntity> get(UUID employeeEntityId);
}
