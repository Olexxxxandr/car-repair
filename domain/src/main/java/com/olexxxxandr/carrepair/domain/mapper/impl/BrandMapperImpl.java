package com.olexxxxandr.carrepair.domain.mapper.impl;

import com.olexxxxandr.carrepair.domain.impl.Brand;
import com.olexxxxandr.carrepair.domain.mapper.BrandMapper;
import com.olexxxxandr.carrepair.persistence.entity.impl.BrandEntity;

public class BrandMapperImpl implements BrandMapper {

    @Override
    public Brand toDomain(BrandEntity entity) {
        var brand = Brand.builder().name(entity.getName()).build();
        brand.setId(entity.getId());
        return brand;
    }

    @Override
    public BrandEntity toEntity(Brand domain) {
        return BrandEntity.builder().id(domain.getId()).name(domain.getName()).build();
    }

    private BrandMapperImpl() {}

    private static class SingletonHolder {
        public static final BrandMapperImpl INSTANCE = new BrandMapperImpl();
    }

    public static BrandMapperImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
