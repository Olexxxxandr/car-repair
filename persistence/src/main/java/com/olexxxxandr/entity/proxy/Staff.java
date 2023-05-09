package com.olexxxxandr.entity.proxy;

import com.olexxxxandr.entity.impl.EmployeeEntity;
import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface Staff {
    List<EmployeeEntity> get(UUID orderId);
}
