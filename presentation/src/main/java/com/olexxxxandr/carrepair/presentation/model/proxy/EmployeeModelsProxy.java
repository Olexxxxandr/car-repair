package com.olexxxxandr.carrepair.presentation.model.proxy;

import com.olexxxxandr.carrepair.domain.proxy.StaffProxy;
import com.olexxxxandr.carrepair.presentation.mapper.impl.ModelMapperFactory;
import com.olexxxxandr.carrepair.presentation.model.impl.EmployeeModel;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class EmployeeModelsProxy implements EmployeeModels {

    private EmployeeModels staffModel;

    @Override
    public List<EmployeeModel> get(UUID orderModelId) {
        if (Objects.isNull(staffModel)) {
            var staffProxy = new StaffProxy();
            staffModel = oi -> staffProxy.get(orderModelId).stream()
                    .map(e -> ModelMapperFactory.getInstance()
                            .getEmployeeModelMapper()
                            .toModel(e))
                    .toList();
        }
        return staffModel.get(orderModelId);
    }
}
