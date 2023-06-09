package com.olexxxxandr.carrepair.presentation.mapper.impl;

import com.olexxxxandr.carrepair.domain.impl.Model;
import com.olexxxxandr.carrepair.presentation.mapper.ModelMapper;
import com.olexxxxandr.carrepair.presentation.model.impl.ModelModel;

public class ModelModelMapper implements ModelMapper<Model, ModelModel> {

    @Override
    public ModelModel toModel(Model model) {
        var brandModelMapper = ModelMapperFactory.getInstance().getBrandModelMapper();
        ModelModel modelModel = ModelModel.builder()
                .brandModel(brandModelMapper.toModel(model.getBrand()))
                .name(model.getName())
                .build();
        modelModel.setId(model.getId());
        return modelModel;
    }

    @Override
    public Model toDomain(ModelModel modelModel) {
        var brandModelMapper = ModelMapperFactory.getInstance().getBrandModelMapper();
        Model model = Model.builder()
                .brand(brandModelMapper.toDomain(modelModel.getBrandModel()))
                .name(modelModel.getName())
                .build();
        model.setId(modelModel.getId());
        return model;
    }
}
