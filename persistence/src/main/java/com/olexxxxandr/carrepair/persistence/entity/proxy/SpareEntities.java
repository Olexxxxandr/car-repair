package com.olexxxxandr.carrepair.persistence.entity.proxy;

import com.olexxxxandr.carrepair.persistence.entity.impl.SpareEntity;
import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface SpareEntities {

    List<SpareEntity> get(UUID orderEntityId);
}
