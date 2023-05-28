package com.olexxxxandr.carrepair.persistence.dao;

import com.olexxxxandr.carrepair.persistence.entity.impl.OrderEntity;
import com.olexxxxandr.carrepair.persistence.entity.impl.ServiceEntity;
import com.olexxxxandr.carrepair.persistence.filter.impl.ServiceFilterDto;
import java.util.List;
import java.util.UUID;

public interface ServiceDao extends GenericDao<Integer, ServiceEntity, ServiceFilterDto> {

    List<OrderEntity> findAllOrders(Integer serviceId);

    void attachToOrder(Integer serviceId, UUID orderId, String description);

    void detachFromOrder(Integer serviceId, UUID orderId);
}
