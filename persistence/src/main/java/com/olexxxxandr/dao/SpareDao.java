package com.olexxxxandr.dao;

import com.olexxxxandr.entity.impl.OrderEntity;
import com.olexxxxandr.entity.impl.SpareEntity;
import com.olexxxxandr.filter.impl.SpareFilterDto;
import java.util.List;
import java.util.UUID;

public interface SpareDao extends GenericDao<UUID, SpareEntity, SpareFilterDto> {

    List<OrderEntity> findAllOrders(UUID spareId);

    void attachToOrder(UUID spareId, UUID orderId, int quantity);

    void detachFromOrder(UUID spareId, UUID orderId);
}
