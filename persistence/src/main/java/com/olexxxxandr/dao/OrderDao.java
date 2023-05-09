package com.olexxxxandr.dao;

import com.olexxxxandr.entity.impl.EmployeeEntity;
import com.olexxxxandr.entity.impl.OrderEntity;
import com.olexxxxandr.entity.impl.ServiceEntity;
import com.olexxxxandr.entity.impl.SpareEntity;
import com.olexxxxandr.filter.impl.OrderFilterDto;
import java.util.List;
import java.util.UUID;

public interface OrderDao extends GenericDao<UUID, OrderEntity, OrderFilterDto> {

    List<EmployeeEntity> findAllStaff(UUID orderId);

    void attachToEmployee(UUID orderId, UUID employeeId);

    void detachFromEmployee(UUID orderId, UUID employeeId);

    List<ServiceEntity> findAllServices(UUID orderId);

    String findServiceOrderDescription(UUID orderId, Integer serviceId);

    void attachToService(UUID orderId, Integer serviceId, String description);

    void detachFromService(UUID orderId, Integer serviceId);

    List<SpareEntity> findAllSpares(UUID orderId);

    int findSpareOrderQuantity(UUID orderId, UUID spareId);

    void attachToSpare(UUID orderId, UUID spareId, int quantity);

    void detachFromSpare(UUID orderId, UUID spareId);
}
