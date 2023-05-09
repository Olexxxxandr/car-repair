package com.olexxxxandr.entity.impl;

import com.olexxxxandr.entity.BaseEntity;
import com.olexxxxandr.entity.Entity;
import com.olexxxxandr.exception.persistance.EntityNotFoundException;
import com.olexxxxandr.factory.DaoFactory;
import java.util.Objects;
import java.util.StringJoiner;

public class PositionEntity extends BaseEntity<Integer> implements Entity {

    private String name;
    private String description;
    private CurrencyEntity currencyEntity;
    private Money salaryPerHour;
    private RoleEntity roleEntity;

    private PositionEntity(
            Integer id, String name, CurrencyEntity currencyEntity, Money salaryPerHour) {
        super(id);
        this.name = name;
        this.currencyEntity = currencyEntity;
        this.salaryPerHour = salaryPerHour;
    }

    public static PositionEntityBuilderId builder() {
        return id ->
                name ->
                        currencyEntity ->
                                salaryPerHour ->
                                        () ->
                                                new PositionEntity(
                                                        id, name, currencyEntity, salaryPerHour);
    }

    @FunctionalInterface
    public interface PositionEntityBuilderId {

        PositionEntityBuilderName id(int id);
    }

    @FunctionalInterface
    public interface PositionEntityBuilderName {

        PositionEntityBuilderCurrencyEntity name(String name);
    }

    @FunctionalInterface
    public interface PositionEntityBuilderCurrencyEntity {

        PositionEntityBuilderSalaryPerHour currencyEntity(CurrencyEntity currencyEntity);
    }

    @FunctionalInterface
    public interface PositionEntityBuilderSalaryPerHour {
        PositionEntityBuilder salaryPerHour(Money salaryPerHour);
    }

    @FunctionalInterface
    public interface PositionEntityBuilder {
        PositionEntity build();
    }

    public String getName() {
        return name;
    }

    public CurrencyEntity getCurrencyEntity() {
        return currencyEntity;
    }

    public Money getSalaryPerHour() {
        return salaryPerHour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public RoleEntity getRoleEntity() {
        if (Objects.isNull(roleEntity)) {
            roleEntity =
                    DaoFactory.getInstance()
                            .getRoleDao()
                            .findOneById(id)
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "Напевно, професія ще не має ролі"));
        }
        return roleEntity;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PositionEntity.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("currencyEntity=" + currencyEntity)
                .add("salaryPerHour=" + salaryPerHour)
                .add("roleEntity=" + roleEntity)
                .add("id=" + id)
                .toString();
    }
}
