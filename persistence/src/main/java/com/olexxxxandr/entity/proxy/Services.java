package com.olexxxxandr.entity.proxy;

import com.olexxxxandr.entity.impl.ServiceEntity;
import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface Services {
    List<ServiceEntity> get(UUID orderId);
}
