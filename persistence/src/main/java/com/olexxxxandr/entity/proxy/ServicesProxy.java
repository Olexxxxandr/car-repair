package com.olexxxxandr.entity.proxy;

import com.olexxxxandr.entity.impl.ServiceEntity;
import com.olexxxxandr.factory.DaoFactory;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ServicesProxy implements Services {

    private Services services;

    @Override
    public List<ServiceEntity> get(UUID orderId) {
        if (Objects.isNull(services)) {
            services =
                    oi ->
                            Collections.unmodifiableList(
                                    DaoFactory.getInstance().getOrderDao().findAllServices(oi));
        }
        return services.get(orderId);
    }
}
