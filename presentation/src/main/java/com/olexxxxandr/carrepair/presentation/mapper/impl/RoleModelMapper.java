package com.olexxxxandr.carrepair.presentation.mapper.impl;

import com.olexxxxandr.carrepair.domain.impl.Role;
import com.olexxxxandr.carrepair.presentation.mapper.ModelMapper;
import com.olexxxxandr.carrepair.presentation.model.impl.RoleModel;

public class RoleModelMapper implements ModelMapper<Role, RoleModel> {

    @Override
    public RoleModel toModel(Role role) {
        return RoleModel.builder()
                .id(role.getId())
                .canEditUsers(role.canEditUsers())
                .canEditSpares(role.canEditSpares())
                .canEditClients(role.canEditClients())
                .canEditServices(role.canEditServices())
                .canEditOrders(role.canEditOrders())
                .canEditPayrolls(role.canEditPayrolls())
                .build();
    }

    @Override
    public Role toDomain(RoleModel roleModel) {
        return Role.builder()
                .id(roleModel.getId())
                .canEditUsers(roleModel.canEditUsers())
                .canEditSpares(roleModel.canEditSpares())
                .canEditClients(roleModel.canEditClients())
                .canEditServices(roleModel.canEditServices())
                .canEditOrders(roleModel.canEditOrders())
                .canEditPayrolls(roleModel.canEditPayrolls())
                .build();
    }

    RoleModelMapper() {}
}
