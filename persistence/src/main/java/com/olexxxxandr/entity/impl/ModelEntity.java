package com.olexxxxandr.entity.impl;

import com.olexxxxandr.entity.BaseEntity;
import com.olexxxxandr.entity.Entity;
import java.util.Objects;
import java.util.StringJoiner;

public class ModelEntity extends BaseEntity<Integer> implements Entity {

    private BrandEntity brandEntity;
    private String name;

    private ModelEntity(int id, BrandEntity brandEntity, String name) {
        super(id);
        this.brandEntity = brandEntity;
        this.name = name;
    }

    public static ModelEntityBuilderId builder() {
        return id -> brandEntity -> name -> () -> new ModelEntity(id, brandEntity, name);
    }

    @FunctionalInterface
    public interface ModelEntityBuilderId {
        ModelEntityBuilderBrandEntity id(int id);
    }

    @FunctionalInterface
    public interface ModelEntityBuilderBrandEntity {
        ModelEntityBuilderName brandEntity(BrandEntity brandEntity);
    }

    @FunctionalInterface
    public interface ModelEntityBuilderName {
        ModelEntityBuilder name(String name);
    }

    @FunctionalInterface
    public interface ModelEntityBuilder {
        ModelEntity build();
    }

    public BrandEntity getBrandEntity() {
        return brandEntity;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ModelEntity that = (ModelEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ModelEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("brandEntity=" + brandEntity)
                .add("name='" + name + "'")
                .toString();
    }
}
