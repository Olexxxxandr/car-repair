package com.olexxxxandr.filter.impl;

import com.olexxxxandr.filter.FilterDto;

public record RoleFilterDto(
        Boolean canEditUsers,
        Boolean canEditSpares,
        Boolean canEditClients,
        Boolean canEditServices,
        Boolean canEditOrders,
        Boolean canEditPayrolls)
        implements FilterDto {}
