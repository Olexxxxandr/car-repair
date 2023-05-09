package com.olexxxxandr.entity.impl;

import com.olexxxxandr.entity.BaseEntity;
import com.olexxxxandr.entity.Entity;
import java.util.Arrays;
import java.util.StringJoiner;

public class ServiceEntity extends BaseEntity<Integer> implements Entity {

    private String name;
    private String description;
    private byte[] photo;
    private CurrencyEntity currencyEntity;
    private Money price;
    // не хороший варіант.
    private String extraFieldDescription;

    private ServiceEntity(
            Integer id,
            String name,
            String description,
            byte[] photo,
            CurrencyEntity currencyEntity,
            Money price) {
        super(id);
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.currencyEntity = currencyEntity;
        this.price = price;
    }

    public static ServiceEntityBuilderId builder() {
        return id ->
                name ->
                        description ->
                                photo ->
                                        currencyEntity ->
                                                price ->
                                                        () ->
                                                                new ServiceEntity(
                                                                        id,
                                                                        name,
                                                                        description,
                                                                        photo,
                                                                        currencyEntity,
                                                                        price);
    }

    @FunctionalInterface
    public interface ServiceEntityBuilderId {
        ServiceEntityBuilderName id(int id);
    }

    @FunctionalInterface
    public interface ServiceEntityBuilderName {
        ServiceEntityBuilderDescription name(String name);
    }

    @FunctionalInterface
    public interface ServiceEntityBuilderDescription {
        ServiceEntityBuilderPhoto description(String description);
    }

    @FunctionalInterface
    public interface ServiceEntityBuilderPhoto {
        ServiceEntityBuilderCurrencyEntity photo(byte[] photo);
    }

    @FunctionalInterface
    public interface ServiceEntityBuilderCurrencyEntity {
        ServiceEntityBuilderPrice currencyEntity(CurrencyEntity currencyEntity);
    }

    @FunctionalInterface
    public interface ServiceEntityBuilderPrice {
        ServiceEntityBuilder price(Money price);
    }

    @FunctionalInterface
    public interface ServiceEntityBuilder {
        ServiceEntity build();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public CurrencyEntity getCurrencyEntity() {
        return currencyEntity;
    }

    public Money getPrice() {
        return price;
    }

    public String getExtraFieldDescription() {
        return extraFieldDescription;
    }

    public void setExtraFieldDescription(String extraFieldDescription) {
        this.extraFieldDescription = extraFieldDescription;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ServiceEntity.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("photo=" + Arrays.toString(photo))
                .add("currencyEntity=" + currencyEntity)
                .add("price=" + price)
                .add("id=" + id)
                .toString();
    }
}
