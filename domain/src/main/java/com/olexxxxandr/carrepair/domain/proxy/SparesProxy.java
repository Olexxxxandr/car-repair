package com.olexxxxandr.carrepair.domain.proxy;

import com.olexxxxandr.carrepair.domain.impl.Spare;
import com.olexxxxandr.carrepair.domain.mapper.impl.SpareMapperImpl;
import com.olexxxxandr.carrepair.persistence.entity.proxy.SpareEntitiesProxy;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class SparesProxy implements Spares {

    private Spares spares;

    @Override
    public List<Spare> get(UUID orderId) {
        if (Objects.isNull(spares)) {
            var spareEntitiesProxy = new SpareEntitiesProxy();
            spares = oi -> spareEntitiesProxy.get(oi).stream()
                    .map(se -> SpareMapperImpl.getInstance().toDomain(se))
                    .toList();
        }
        return spares.get(orderId);
    }
}
