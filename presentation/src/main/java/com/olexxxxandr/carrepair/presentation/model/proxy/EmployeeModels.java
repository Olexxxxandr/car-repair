package com.olexxxxandr.carrepair.presentation.model.proxy;

import com.olexxxxandr.carrepair.presentation.model.impl.EmployeeModel;
import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface EmployeeModels {
    List<EmployeeModel> get(UUID orderModelId);
}
