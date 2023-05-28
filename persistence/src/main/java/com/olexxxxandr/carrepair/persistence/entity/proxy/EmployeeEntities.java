package com.olexxxxandr.carrepair.persistence.entity.proxy;

import com.olexxxxandr.carrepair.persistence.entity.impl.EmployeeEntity;
import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface EmployeeEntities {
    List<EmployeeEntity> get(UUID orderEntityId);
}
