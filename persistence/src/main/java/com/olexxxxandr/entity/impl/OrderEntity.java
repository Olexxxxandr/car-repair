package com.olexxxxandr.entity.impl;

import com.olexxxxandr.entity.BaseEntity;
import com.olexxxxandr.entity.Entity;
import com.olexxxxandr.entity.proxy.Services;
import com.olexxxxandr.entity.proxy.ServicesProxy;
import com.olexxxxandr.entity.proxy.Spares;
import com.olexxxxandr.entity.proxy.SparesProxy;
import com.olexxxxandr.entity.proxy.Staff;
import com.olexxxxandr.entity.proxy.StaffProxy;
import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

public class OrderEntity extends BaseEntity<UUID> implements Entity {

    private ClientEntity clientEntity;
    private CarEntity carEntity;
    private DiscountEntity discountEntity;
    private Money price;
    private PaymentType paymentType;
    private LocalDateTime paymentAt;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private Staff staff;
    private Services services;
    private Spares spares;

    private OrderEntity(
            UUID id,
            ClientEntity clientEntity,
            CarEntity carEntity,
            DiscountEntity discountEntity,
            Money price,
            PaymentType paymentType,
            LocalDateTime paymentAt) {
        super(id);
        this.clientEntity = clientEntity;
        this.carEntity = carEntity;
        this.discountEntity = discountEntity;
        this.price = price;
        this.paymentType = paymentType;
        this.paymentAt = paymentAt;
        this.staff = new StaffProxy();
        this.services = new ServicesProxy();
        this.spares = new SparesProxy();
    }

    public static OrderEntityBuilderId builder() {
        return id ->
                clientEntity ->
                        carEntity ->
                                discountEntity ->
                                        price ->
                                                paymentType ->
                                                        paymentAt ->
                                                                () ->
                                                                        new OrderEntity(
                                                                                id,
                                                                                clientEntity,
                                                                                carEntity,
                                                                                discountEntity,
                                                                                price,
                                                                                paymentType,
                                                                                paymentAt);
    }

    @FunctionalInterface
    public interface OrderEntityBuilderId {
        OrderEntityBuilderClientEntity id(UUID id);
    }

    @FunctionalInterface
    public interface OrderEntityBuilderClientEntity {
        OrderEntityBuilderCarEntity clientEntity(ClientEntity clientEntity);
    }

    @FunctionalInterface
    public interface OrderEntityBuilderCarEntity {
        OrderEntityBuilderDiscountEntity carEntity(CarEntity carEntity);
    }

    @FunctionalInterface
    public interface OrderEntityBuilderDiscountEntity {
        OrderEntityBuilderPrice discountEntity(DiscountEntity discountEntity);
    }

    @FunctionalInterface
    public interface OrderEntityBuilderPrice {
        OrderEntityBuilderPaymentType price(Money price);
    }

    @FunctionalInterface
    public interface OrderEntityBuilderPaymentType {
        OrderEntityBuilderPaymentAt paymentType(PaymentType paymentType);
    }

    @FunctionalInterface
    public interface OrderEntityBuilderPaymentAt {
        OrderEntityBuilder paymentAt(LocalDateTime paymentAt);
    }

    @FunctionalInterface
    public interface OrderEntityBuilder {
        OrderEntity build();
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public CarEntity getCarEntity() {
        return carEntity;
    }

    public DiscountEntity getDiscountEntity() {
        return discountEntity;
    }

    public Money getPrice() {
        return price;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public LocalDateTime getPaymentAt() {
        return paymentAt;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Staff getProxyStaff() {
        return staff;
    }

    public List<EmployeeEntity> getStaff() {
        return staff.get(id);
    }

    public Services getProxyServices() {
        return services;
    }

    public List<ServiceEntity> getServices() {
        return services.get(id);
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public List<SpareEntity> getSpares() {
        return spares.get(id);
    }

    public void setSpares(Spares spares) {
        this.spares = spares;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderEntity.class.getSimpleName() + "[", "]")
                .add("clientEntity=" + clientEntity)
                .add("carEntity=" + carEntity)
                .add("discountEntity=" + discountEntity)
                .add("price=" + price)
                .add("paymentType=" + paymentType)
                .add("paymentAt=" + paymentAt)
                .add("updatedAt=" + updatedAt)
                .add("createdAt=" + createdAt)
                .add("staff=" + staff)
                .add("services=" + services)
                .add("spares=" + spares)
                .add("id=" + id)
                .toString();
    }

    public enum PaymentType {
        CARD("карта"),
        CASH("готівка");

        private String name;

        PaymentType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
