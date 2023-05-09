package com.olexxxxandr.entity.proxy;

import com.olexxxxandr.entity.impl.OrderEntity;
import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface Orders {

    List<OrderEntity> get(UUID employeeId);
}
