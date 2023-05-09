package com.olexxxxandr.entity.proxy;

import com.olexxxxandr.entity.impl.OrderEntity;
import com.olexxxxandr.factory.DaoFactory;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrdersProxy implements Orders {

    private Orders orders;

    @Override
    public List<OrderEntity> get(UUID employeeId) {
        if (Objects.isNull(orders)) {
            orders =
                    ei ->
                            Collections.unmodifiableList(
                                    DaoFactory.getInstance()
                                            .getEmployeeDao()
                                            .findAllOrders(employeeId));
        }
        return orders.get(employeeId);
    }
}
