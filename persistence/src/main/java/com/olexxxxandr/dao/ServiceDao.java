package com.olexxxxandr.dao;

import com.olexxxxandr.entity.impl.OrderEntity;
import com.olexxxxandr.entity.impl.ServiceEntity;
import com.olexxxxandr.filter.impl.ServiceFilterDto;
import java.util.List;
import java.util.UUID;

public interface ServiceDao extends GenericDao<Integer, ServiceEntity, ServiceFilterDto> {

    List<OrderEntity> findAllOrders(Integer serviceId);

    void attachToOrder(Integer serviceId, UUID orderId, String description);

    void detachFromOrder(Integer serviceId, UUID orderId);
}
