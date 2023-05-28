package com.olexxxxandr.carrepair.domain.repository;

import com.olexxxxandr.carrepair.domain.impl.Car;
import com.olexxxxandr.carrepair.domain.impl.Client;
import com.olexxxxandr.carrepair.domain.impl.Discount;
import com.olexxxxandr.carrepair.domain.impl.Employee;
import com.olexxxxandr.carrepair.domain.impl.Money;
import com.olexxxxandr.carrepair.domain.impl.Order;
import com.olexxxxandr.carrepair.domain.impl.Service;
import com.olexxxxandr.carrepair.domain.impl.Spare;
import com.olexxxxandr.carrepair.domain.impl.Workroom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends GenericRepository<UUID, Order> {

    /**
     * Get the entire collection of entities by filtering in RDMS.
     *
     * @return collection of Domains
     */
    List<Order> getAllWhere(
            Client client,
            Car car,
            Discount discount,
            Money price,
            String paymentType,
            LocalDateTime paymentAt,
            LocalDateTime updatedAt,
            LocalDateTime createdAt);

    List<Order> getAllByWorkroom();

    List<Order> getAllByWorkroom(Workroom curentWorkroom);

    List<Order> getAllByWorkroomWhere(
            Workroom workroom,
            Client client,
            Car car,
            Discount discount,
            Money price,
            String paymentType,
            LocalDateTime paymentAt,
            LocalDateTime updatedAt,
            LocalDateTime createdAt);

    List<Order> getAllByWorkroomWhere(
            Client client,
            Car car,
            Discount discount,
            Money price,
            String paymentType,
            LocalDateTime paymentAt,
            LocalDateTime updatedAt,
            LocalDateTime createdAt);

    List<Employee> getAllStaff(UUID orderId);

    void attachToEmployee(UUID orderId, UUID employeeId);

    void detachFromEmployee(UUID orderId, UUID employeeId);

    List<Service> getAllServices(UUID orderId);

    String getServiceOrderDescription(UUID orderId, Integer serviceId);

    void attachToService(UUID orderId, Integer serviceId, String description);

    void detachFromService(UUID orderId, Integer serviceId);

    List<Spare> getAllSpares(UUID orderId);

    int getSpareOrderQuantity(UUID orderId, UUID spareId);

    void attachToSpare(UUID orderId, UUID spareId, int quantity);

    void detachFromSpare(UUID orderId, UUID spareId);
}
