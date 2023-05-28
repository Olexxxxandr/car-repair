package com.olexxxxandr.carrepair.domain.mapper.impl;

import com.olexxxxandr.carrepair.domain.impl.Role;
import com.olexxxxandr.carrepair.domain.mapper.RoleMapper;
import com.olexxxxandr.carrepair.persistence.entity.impl.RoleEntity;

public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role toDomain(RoleEntity entity) {
        return Role.builder()
                .id(entity.getId())
                .canEditUsers(entity.canEditUsers())
                .canEditSpares(entity.canEditSpares())
                .canEditClients(entity.canEditClients())
                .canEditServices(entity.canEditServices())
                .canEditOrders(entity.canEditOrders())
                .canEditPayrolls(entity.canEditPayrolls())
                .build();
    }

    @Override
    public RoleEntity toEntity(Role domain) {
        return RoleEntity.builder()
                .id(domain.getId())
                .canEditUsers(domain.canEditUsers())
                .canEditSpares(domain.canEditSpares())
                .canEditClients(domain.canEditClients())
                .canEditServices(domain.canEditServices())
                .canEditOrders(domain.canEditOrders())
                .canEditPayrolls(domain.canEditPayrolls())
                .build();
    }

    private RoleMapperImpl() {}

    private static class SingletonHolder {
        public static final RoleMapperImpl INSTANCE = new RoleMapperImpl();
    }

    public static RoleMapperImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
