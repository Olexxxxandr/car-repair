package com.olexxxxandr.carrepair.domain.repository;

import com.olexxxxandr.carrepair.domain.impl.Address;
import com.olexxxxandr.carrepair.domain.impl.Employee;
import com.olexxxxandr.carrepair.domain.impl.Order;
import com.olexxxxandr.carrepair.domain.impl.Position;
import com.olexxxxandr.carrepair.domain.impl.Workroom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends GenericRepository<UUID, Employee> {

    /**
     * Get the entire collection of entities by filtering in RDMS.
     *
     * @return collection of Entities
     */
    List<Employee> getAllWhere(
            Address address,
            Workroom workroom,
            Position position,
            String firstName,
            String lastName,
            String middleName,
            LocalDateTime updatedAt,
            LocalDateTime createdAt);

    List<Employee> getAllByWorkroom(Workroom workroom);

    List<Employee> getAllByWorkroom();

    List<Order> getAllOrders(UUID employeeId);

    void attachToOrder(UUID employeeId, UUID orderId);

    void detachFromOrder(UUID employeeId, UUID orderId);
}
