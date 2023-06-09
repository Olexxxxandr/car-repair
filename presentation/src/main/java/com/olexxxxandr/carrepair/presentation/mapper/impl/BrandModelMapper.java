package com.olexxxxandr.carrepair.presentation.mapper.impl;

import com.olexxxxandr.carrepair.domain.impl.Brand;
import com.olexxxxandr.carrepair.presentation.mapper.ModelMapper;
import com.olexxxxandr.carrepair.presentation.model.impl.BrandModel;

public class BrandModelMapper implements ModelMapper<Brand, BrandModel> {

    @Override
    public BrandModel toModel(Brand brand) {
        BrandModel brandModel = BrandModel.builder().name(brand.getName()).build();
        brandModel.setId(brand.getId());
        return brandModel;
    }

    @Override
    public Brand toDomain(BrandModel brandModel) {
        Brand brand = Brand.builder().name(brandModel.getName()).build();
        brand.setId(brandModel.getId());
        return brand;
    }

    BrandModelMapper() {}
}
