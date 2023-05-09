package com.olexxxxandr.dao;

import com.olexxxxandr.entity.impl.PayrollEntity;
import com.olexxxxandr.filter.impl.PayrollFilterDto;
import java.util.UUID;

public interface PayrollDao extends GenericDao<UUID, PayrollEntity, PayrollFilterDto> {}
