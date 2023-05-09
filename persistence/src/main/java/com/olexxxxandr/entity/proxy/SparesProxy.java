package com.olexxxxandr.entity.proxy;

import com.olexxxxandr.entity.impl.SpareEntity;
import com.olexxxxandr.factory.DaoFactory;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class SparesProxy implements Spares {

    private Spares spares;

    @Override
    public List<SpareEntity> get(UUID orderId) {
        if (Objects.isNull(spares)) {
            spares =
                    oi ->
                            Collections.unmodifiableList(
                                    DaoFactory.getInstance().getOrderDao().findAllSpares(oi));
        }
        return spares.get(orderId);
    }
}
