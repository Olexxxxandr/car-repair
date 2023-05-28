package com.olexxxxandr.carrepair.domain.mapper.impl;

import com.olexxxxandr.carrepair.domain.impl.Address;
import com.olexxxxandr.carrepair.domain.mapper.AddressMapper;
import com.olexxxxandr.carrepair.persistence.entity.impl.AddressEntity;

public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toDomain(AddressEntity entity) {
        var address = Address.builder()
                .country(entity.getCountry())
                .region(entity.getRegion())
                .city(entity.getCity())
                .street(entity.getStreet())
                .home(entity.getHome())
                .build();
        address.setId(entity.getId());
        return address;
    }

    @Override
    public AddressEntity toEntity(Address domain) {
        return AddressEntity.builder()
                .id(domain.getId())
                .country(domain.getCountry())
                .region(domain.getRegion())
                .city(domain.getCity())
                .street(domain.getStreet())
                .home(domain.getHome())
                .build();
    }

    private AddressMapperImpl() {}

    private static class SingletonHolder {
        public static final AddressMapperImpl INSTANCE = new AddressMapperImpl();
    }

    public static AddressMapperImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
