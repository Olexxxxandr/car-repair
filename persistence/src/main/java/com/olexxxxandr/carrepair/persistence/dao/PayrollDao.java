package com.olexxxxandr.carrepair.persistence.dao;

import com.olexxxxandr.carrepair.persistence.entity.impl.PayrollEntity;
import com.olexxxxandr.carrepair.persistence.entity.impl.WorkroomEntity;
import com.olexxxxandr.carrepair.persistence.filter.impl.PayrollFilterDto;
import java.util.List;
import java.util.UUID;

public interface PayrollDao extends GenericDao<UUID, PayrollEntity, PayrollFilterDto> {
    List<PayrollEntity> findAllByWorkroomEntity(WorkroomEntity workroomEntity);

    List<PayrollEntity> findAllByWorkroomEntity(PayrollFilterDto filter, WorkroomEntity workroomEntity);
}
