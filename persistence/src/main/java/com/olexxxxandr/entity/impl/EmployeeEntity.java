package com.olexxxxandr.entity.impl;

import com.olexxxxandr.entity.BaseEntity;
import com.olexxxxandr.entity.Entity;
import com.olexxxxandr.entity.proxy.Orders;
import com.olexxxxandr.entity.proxy.OrdersProxy;
import com.olexxxxandr.exception.persistance.EntityNotFoundException;
import com.olexxxxandr.factory.DaoFactory;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class EmployeeEntity extends BaseEntity<UUID> implements Entity {

    private AddressEntity addressEntity;
    private WorkroomEntity workroomEntity;
    private PositionEntity positionEntity;
    private String firstName;
    private String lastName;
    private String middleName;
    private byte[] photo;
    private byte[] passportDocCopy;
    private byte[] bankNumberDocCopy;
    private byte[] otherDocCopy;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private UserEntity userEntity;
    private Orders orders;

    private EmployeeEntity(
            UUID id,
            AddressEntity addressEntity,
            WorkroomEntity workroomEntity,
            PositionEntity positionEntity,
            String firstName,
            String lastName,
            byte[] photo,
            byte[] passportDocCopy) {
        super(id);
        this.addressEntity = addressEntity;
        this.workroomEntity = workroomEntity;
        this.positionEntity = positionEntity;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
        this.passportDocCopy = passportDocCopy;
        this.orders = new OrdersProxy();
    }

    public static EmployeeEntityBuilderId builder() {
        return id ->
                addressEntity ->
                        workroomEntity ->
                                positionEntity ->
                                        firstName ->
                                                lastName ->
                                                        photo ->
                                                                passportDocCopy ->
                                                                        () ->
                                                                                new EmployeeEntity(
                                                                                        id,
                                                                                        addressEntity,
                                                                                        workroomEntity,
                                                                                        positionEntity,
                                                                                        firstName,
                                                                                        lastName,
                                                                                        photo,
                                                                                        passportDocCopy);
    }

    @FunctionalInterface
    public interface EmployeeEntityBuilderId {
        EmployeeEntityBuilderAddressEntity id(UUID id);
    }

    @FunctionalInterface
    public interface EmployeeEntityBuilderAddressEntity {
        EmployeeEntityBuilderWorkroomEntity addressEntity(AddressEntity addressEntity);
    }

    @FunctionalInterface
    public interface EmployeeEntityBuilderWorkroomEntity {
        EmployeeEntityBuilderPositionEntity workroomEntity(WorkroomEntity workroomEntity);
    }

    @FunctionalInterface
    public interface EmployeeEntityBuilderPositionEntity {
        EmployeeEntityBuilderFirstName positionEntity(PositionEntity positionEntity);
    }

    @FunctionalInterface
    public interface EmployeeEntityBuilderFirstName {
        EmployeeEntityBuilderLastName firstName(String firstName);
    }

    @FunctionalInterface
    public interface EmployeeEntityBuilderLastName {
        EmployeeEntityBuilderPhoto lastName(String lastName);
    }

    @FunctionalInterface
    public interface EmployeeEntityBuilderPhoto {
        EmployeeEntityPassportDocCopy photo(byte[] photo);
    }

    @FunctionalInterface
    public interface EmployeeEntityPassportDocCopy {
        EmployeeEntityBuilder passportDocCopy(byte[] passportDocCopy);
    }

    @FunctionalInterface
    public interface EmployeeEntityBuilder {
        EmployeeEntity build();
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public WorkroomEntity getWorkroomEntity() {
        return workroomEntity;
    }

    public PositionEntity getPositionEntity() {
        return positionEntity;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public byte[] getPassportDocCopy() {
        return passportDocCopy;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public byte[] getBankNumberDocCopy() {
        return bankNumberDocCopy;
    }

    public void setBankNumberDocCopy(byte[] bankNumberDocCopy) {
        this.bankNumberDocCopy = bankNumberDocCopy;
    }

    public byte[] getOtherDocCopy() {
        return otherDocCopy;
    }

    public void setOtherDocCopy(byte[] otherDocCopy) {
        this.otherDocCopy = otherDocCopy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UserEntity getUserEntity() {
        if (Objects.isNull(userEntity)) {
            userEntity =
                    DaoFactory.getInstance()
                            .getUserDao()
                            .findOneById(id)
                            .orElseThrow(
                                    () ->
                                            new EntityNotFoundException(
                                                    "Напевно, робітник ще не має облікового"
                                                            + " запису"));
        }
        return userEntity;
    }

    public Orders getProxyOrders() {
        return orders;
    }

    public List<OrderEntity> getOrders() {
        return orders.get(id);
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EmployeeEntity.class.getSimpleName() + "[", "]")
                .add("addressEntity=" + addressEntity)
                .add("workroomEntity=" + workroomEntity)
                .add("positionEntity=" + positionEntity)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("middleName='" + middleName + "'")
                .add("photo=" + Arrays.toString(photo))
                .add("passportDocCopy=" + Arrays.toString(passportDocCopy))
                .add("bankNumberDocCopy=" + Arrays.toString(bankNumberDocCopy))
                .add("otherDocCopy=" + Arrays.toString(otherDocCopy))
                .add("updatedAt=" + updatedAt)
                .add("createdAt=" + createdAt)
                .add("userEntity=" + userEntity)
                .add("id=" + id)
                .toString();
    }
}
