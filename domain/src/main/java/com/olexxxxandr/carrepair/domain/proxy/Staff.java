package com.olexxxxandr.carrepair.domain.proxy;

import com.olexxxxandr.carrepair.domain.impl.Employee;
import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface Staff {
    List<Employee> get(UUID orderId);
}
