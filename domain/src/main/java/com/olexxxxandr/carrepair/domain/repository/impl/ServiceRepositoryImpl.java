package com.olexxxxandr.carrepair.domain.repository.impl;

import com.olexxxxandr.carrepair.domain.exception.DomainAddException;
import com.olexxxxandr.carrepair.domain.exception.DomainNotFoundException;
import com.olexxxxandr.carrepair.domain.impl.Currency;
import com.olexxxxandr.carrepair.domain.impl.Money;
import com.olexxxxandr.carrepair.domain.impl.Order;
import com.olexxxxandr.carrepair.domain.impl.Service;
import com.olexxxxandr.carrepair.domain.mapper.MapperFactory;
import com.olexxxxandr.carrepair.domain.mapper.ServiceMapper;
import com.olexxxxandr.carrepair.domain.repository.ServiceRepository;
import com.olexxxxandr.carrepair.domain.validator.service.ServiceValidatorChain;
import com.olexxxxandr.carrepair.persistence.DaoFactory;
import com.olexxxxandr.carrepair.persistence.dao.ServiceDao;
import com.olexxxxandr.carrepair.persistence.entity.impl.ServiceEntity;
import com.olexxxxandr.carrepair.persistence.filter.impl.ServiceFilterDto;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class ServiceRepositoryImpl implements ServiceRepository {

    private final ServiceDao serviceDao;
    private final ServiceMapper serviceMapper;
    private final ServiceValidatorChain serviceValidatorChain;

    public ServiceRepositoryImpl() {
        serviceDao = DaoFactory.getInstance().getServiceDao();
        serviceMapper = MapperFactory.getInstance().getServiceMapper();
        serviceValidatorChain = ServiceValidatorChain.getInstance();
    }

    /**
     * Get the entire collection of entities by filtering in RDMS.
     *
     * @return collection of Domains
     */
    @Override
    public List<Service> getAllWhere(String name, Currency currency, Money price) {
        var currencyMapper = MapperFactory.getInstance().getCurrencyMapper();
        var filter = new ServiceFilterDto(
                name,
                Objects.nonNull(currency) ? currencyMapper.toEntity(currency) : null,
                Objects.nonNull(price)
                        ? new com.olexxxxandr.carrepair.persistence.entity.impl.Money(
                                price.wholePart(), price.decimalPart())
                        : null);
        List<ServiceEntity> serviceEntities = serviceDao.findAll(filter);
        return serviceEntities.stream().map(serviceMapper::toDomain).toList();
    }

    /**
     * Get a domain object by identifier.
     *
     * @param id primary key identifier
     * @return D domain object
     */
    @Override
    public Service get(Integer id) {
        ServiceEntity serviceEntity = serviceDao
                .findOneById(id)
                .orElseThrow(() -> new DomainNotFoundException("Не вдалось знайти послугу по ідентифікатору"));
        return serviceMapper.toDomain(serviceEntity);
    }

    /**
     * Get the entire collection of entities.
     *
     * @return collection of Entities
     */
    @Override
    public List<Service> getAll() {
        List<ServiceEntity> serviceEntities = serviceDao.findAll();
        return serviceEntities.stream().map(serviceMapper::toDomain).toList();
    }

    /**
     * Save the entity to the database table.
     *
     * @param service persistent entity
     * @return entity, with identifier of the last added record from the database
     */
    @Override
    public Service add(Service service) {
        if (!serviceValidatorChain.validate(service)) {
            throw new DomainAddException("Не вдалось додати послугу із-за не валідних даних");
        }
        service.setId(null);
        var serviceEntity = serviceMapper.toEntity(service);
        serviceEntity = serviceDao.save(serviceEntity);
        return serviceMapper.toDomain(serviceEntity);
    }

    /**
     * Update the domain to the database table.
     *
     * @param id ідентифікатор
     * @param service persistent entity
     */
    @Override
    public void set(Integer id, Service service) {
        if (!serviceValidatorChain.validate(service)) {
            throw new DomainAddException("Не вдалось оновити послугу із-за не валідних даних");
        }
        service.setId(id);
        get(id);

        var serviceEntity = serviceMapper.toEntity(service);
        serviceDao.save(serviceEntity);
    }

    /**
     * Delete domain object from collection.
     *
     * @param id primary key identifier
     */
    @Override
    public void remove(Integer id) {
        get(id);
        serviceDao.remove(id);
    }

    @Override
    public Map<String, List<String>> getValidationMessages() {
        return serviceValidatorChain.getValidationMessages();
    }

    @Override
    public List<Order> findAllOrders(Integer serviceId) {
        var orderEntities = serviceDao.findAllOrders(serviceId);
        var orderMapper = MapperFactory.getInstance().getOrderMapper();
        return orderEntities.stream().map(orderMapper::toDomain).toList();
    }

    @Override
    public void attachToOrder(Integer serviceId, UUID orderId, String description) {
        serviceDao.attachToOrder(serviceId, orderId, description);
    }

    @Override
    public void detachFromOrder(Integer serviceId, UUID orderId) {
        serviceDao.detachFromOrder(serviceId, orderId);
    }
}
