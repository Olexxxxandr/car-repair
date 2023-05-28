package com.olexxxxandr.carrepair.domain.proxy;

import com.olexxxxandr.carrepair.domain.impl.Spare;
import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface Spares {

    List<Spare> get(UUID orderId);
}
