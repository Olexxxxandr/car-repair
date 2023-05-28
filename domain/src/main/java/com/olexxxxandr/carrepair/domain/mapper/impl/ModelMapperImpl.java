package com.olexxxxandr.carrepair.domain.mapper.impl;

import com.olexxxxandr.carrepair.domain.impl.Model;
import com.olexxxxandr.carrepair.domain.mapper.MapperFactory;
import com.olexxxxandr.carrepair.domain.mapper.ModelMapper;
import com.olexxxxandr.carrepair.persistence.entity.impl.ModelEntity;

public class ModelMapperImpl implements ModelMapper {

    @Override
    public Model toDomain(ModelEntity entity) {
        var model = Model.builder()
                .brand(MapperFactory.getInstance().getBrandMapper().toDomain(entity.getBrandEntity()))
                .name(entity.getName())
                .build();
        model.setId(entity.getId());
        return model;
    }

    @Override
    public ModelEntity toEntity(Model domain) {
        return ModelEntity.builder()
                .id(domain.getId())
                .brandEntity(MapperFactory.getInstance().getBrandMapper().toEntity(domain.getBrand()))
                .name(domain.getName())
                .build();
    }

    private ModelMapperImpl() {}

    private static class SingletonHolder {
        public static final ModelMapperImpl INSTANCE = new ModelMapperImpl();
    }

    public static ModelMapperImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
