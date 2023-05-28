package com.olexxxxandr.carrepair.domain.repository;

import com.olexxxxandr.carrepair.domain.impl.Employee;
import com.olexxxxandr.carrepair.domain.impl.Money;
import com.olexxxxandr.carrepair.domain.impl.Payroll;
import com.olexxxxandr.carrepair.domain.impl.Workroom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PayrollRepository extends GenericRepository<UUID, Payroll> {

    /**
     * Get the entire collection of entities by filtering in RDMS.
     *
     * @return collection of Domains
     */
    List<Payroll> getAllWhere(
            Employee employee,
            String periodType,
            Integer hourCount,
            Money salary,
            LocalDateTime paymentAt,
            LocalDateTime updatedAt,
            LocalDateTime createdAt);

    List<Payroll> getAllByWorkroom(Workroom workroom);

    List<Payroll> getAllByWorkroom();

    List<Payroll> getAllByWorkroomWhere(
            Workroom workroom,
            Employee employee,
            String periodType,
            Integer hourCount,
            Money salary,
            LocalDateTime paymentAt,
            LocalDateTime updatedAt,
            LocalDateTime createdAt);

    List<Payroll> getAllByWorkroomWhere(
            Employee employee,
            String periodType,
            Integer hourCount,
            Money salary,
            LocalDateTime paymentAt,
            LocalDateTime updatedAt,
            LocalDateTime createdAt);
}
