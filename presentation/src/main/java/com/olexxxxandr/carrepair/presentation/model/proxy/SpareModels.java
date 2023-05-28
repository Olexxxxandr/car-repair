package com.olexxxxandr.carrepair.presentation.model.proxy;

import com.olexxxxandr.carrepair.presentation.model.impl.SpareModel;
import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface SpareModels {

    List<SpareModel> get(UUID orderModelId);
}
