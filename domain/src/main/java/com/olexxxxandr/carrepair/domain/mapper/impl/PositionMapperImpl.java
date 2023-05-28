package com.olexxxxandr.carrepair.domain.mapper.impl;

import com.olexxxxandr.carrepair.domain.impl.Money;
import com.olexxxxandr.carrepair.domain.impl.Position;
import com.olexxxxandr.carrepair.domain.mapper.MapperFactory;
import com.olexxxxandr.carrepair.domain.mapper.PositionMapper;
import com.olexxxxandr.carrepair.persistence.entity.impl.PositionEntity;
import java.util.Objects;

public class PositionMapperImpl implements PositionMapper {

    @Override
    public Position toDomain(PositionEntity entity) {
        var roleMapper = MapperFactory.getInstance().getRoleMapper();
        var position = Position.builder()
                .name(entity.getName())
                .currency(MapperFactory.getInstance().getCurrencyMapper().toDomain(entity.getCurrencyEntity()))
                .salaryPerHour(new Money(
                        entity.getSalaryPerHour().wholePart(),
                        entity.getSalaryPerHour().decimalPart()))
                .build();
        position.setId(entity.getId());
        position.setDescription(entity.getDescription());
        if (Objects.nonNull(entity.getRoleEntity())) {
            position.setRole(roleMapper.toDomain(entity.getRoleEntity()));
        }

        return position;
    }

    @Override
    public PositionEntity toEntity(Position position) {
        var roleMapper = MapperFactory.getInstance().getRoleMapper();
        var positionEntity = PositionEntity.builder()
                .id(position.getId())
                .name(position.getName())
                .currencyEntity(MapperFactory.getInstance().getCurrencyMapper().toEntity(position.getCurrency()))
                .salaryPerHour(new com.olexxxxandr.carrepair.persistence.entity.impl.Money(
                        position.getSalaryPerHour().wholePart(),
                        position.getSalaryPerHour().decimalPart()))
                .build();
        positionEntity.setId(position.getId());
        positionEntity.setDescription(position.getDescription());
        if (Objects.nonNull(position.getRole())) {
            positionEntity.setRoleEntity(roleMapper.toEntity(position.getRole()));
        }
        return positionEntity;
    }

    private PositionMapperImpl() {}

    private static class SingletonHolder {
        public static final PositionMapperImpl INSTANCE = new PositionMapperImpl();
    }

    public static PositionMapperImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
