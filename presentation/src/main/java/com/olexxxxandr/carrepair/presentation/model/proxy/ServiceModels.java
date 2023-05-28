package com.olexxxxandr.carrepair.presentation.model.proxy;

import com.olexxxxandr.carrepair.presentation.model.impl.ServiceModel;
import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface ServiceModels {

    List<ServiceModel> get(UUID orderModelId);
}
