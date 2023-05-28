package com.olexxxxandr.carrepair.domain.proxy;

import com.olexxxxandr.carrepair.domain.impl.Order;
import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface Orders {

    List<Order> get(UUID employeeId);
}
