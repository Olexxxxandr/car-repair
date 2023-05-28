package com.olexxxxandr.carrepair.persistence.entity.proxy;

import com.olexxxxandr.carrepair.persistence.DaoFactory;
import com.olexxxxandr.carrepair.persistence.entity.impl.OrderEntity;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrderEntitiesProxy implements OrderEntities {

    private OrderEntities orderEntities;

    @Override
    public List<OrderEntity> get(UUID employeeEntityId) {
        if (Objects.isNull(orderEntities)) {
            orderEntities = oe -> Collections.unmodifiableList(
                    DaoFactory.getInstance().getEmployeeDao().findAllOrders(oe));
        }
        return orderEntities.get(employeeEntityId);
    }
}
