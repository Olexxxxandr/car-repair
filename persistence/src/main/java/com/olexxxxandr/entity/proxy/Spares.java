package com.olexxxxandr.entity.proxy;

import com.olexxxxandr.entity.impl.SpareEntity;
import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface Spares {

    List<SpareEntity> get(UUID orderId);
}
