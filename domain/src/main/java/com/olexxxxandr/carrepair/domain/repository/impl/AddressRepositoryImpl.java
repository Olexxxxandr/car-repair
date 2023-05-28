package com.olexxxxandr.carrepair.domain.repository.impl;

import com.olexxxxandr.carrepair.domain.exception.AuthException;
import com.olexxxxandr.carrepair.domain.exception.DomainAddException;
import com.olexxxxandr.carrepair.domain.exception.DomainNotFoundException;
import com.olexxxxandr.carrepair.domain.impl.Address;
import com.olexxxxandr.carrepair.domain.impl.Workroom;
import com.olexxxxandr.carrepair.domain.mapper.AddressMapper;
import com.olexxxxandr.carrepair.domain.mapper.MapperFactory;
import com.olexxxxandr.carrepair.domain.repository.AddressRepository;
import com.olexxxxandr.carrepair.domain.service.AuthService;
import com.olexxxxandr.carrepair.domain.service.impl.AuthServiceImpl;
import com.olexxxxandr.carrepair.domain.validator.address.AddressValidatorChain;
import com.olexxxxandr.carrepair.persistence.DaoFactory;
import com.olexxxxandr.carrepair.persistence.dao.AddressDao;
import com.olexxxxandr.carrepair.persistence.entity.impl.AddressEntity;
import com.olexxxxandr.carrepair.persistence.entity.impl.WorkroomEntity;
import com.olexxxxandr.carrepair.persistence.filter.impl.AddressFilterDto;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class AddressRepositoryImpl implements AddressRepository {

    private final AddressDao addressDao;
    private final AddressMapper addressMapper;
    private final AddressValidatorChain addressValidatorChain;

    public AddressRepositoryImpl() {
        addressDao = DaoFactory.getInstance().getAddressDao();
        addressMapper = MapperFactory.getInstance().getAddressMapper();
        addressValidatorChain = AddressValidatorChain.getInstance();
    }

    /**
     * Get the entire collection of entities by filtering in RDMS.
     *
     * @return collection of Domains
     */
    @Override
    public List<Address> getAllWhere(String country, String region, String city, String street, String home) {
        var filter = new AddressFilterDto(country, region, city, street, home);
        List<AddressEntity> addressEntities = addressDao.findAll(filter);
        return addressEntities.stream().map(addressMapper::toDomain).toList();
    }

    /**
     * Get a domain object by identifier.
     *
     * @param id primary key identifier
     * @return D domain object
     */
    @Override
    public Address get(UUID id) {
        AddressEntity addressEntity = addressDao
                .findOneById(id)
                .orElseThrow(() -> new DomainNotFoundException("Не вдалось знайти адресу по ідентифікатору"));

        return addressMapper.toDomain(addressEntity);
    }

    /**
     * Get the entire collection of entities.
     *
     * @return collection of Entities
     */
    @Override
    public List<Address> getAll() {
        List<AddressEntity> addressEntities = addressDao.findAll();
        return addressEntities.stream().map(addressMapper::toDomain).toList();
    }

    @Override
    public List<Address> getAllByWorkroom(Workroom workroom) {
        WorkroomEntity workroomEntity =
                MapperFactory.getInstance().getWorkroomMapper().toEntity(workroom);
        List<AddressEntity> addressEntities = addressDao.findAllByWorkroomEntity(workroomEntity);
        return addressEntities.stream().map(addressMapper::toDomain).toList();
    }

    @Override
    public List<Address> getAllByWorkroom() {
        AuthService authService = AuthServiceImpl.getInstance();
        Workroom curentWorkroom = authService.getCurentWorkroom();
        if (Objects.isNull(curentWorkroom)) {
            throw new AuthException("Щоб отримати всі адреси по відділу, спочатку аутентифікуйтесь");
        }
        return getAllByWorkroom(curentWorkroom);
    }

    @Override
    public List<Address> getAllByWorkroomWhere(
            Workroom workroom, String country, String region, String city, String street, String home) {
        WorkroomEntity workroomEntity =
                MapperFactory.getInstance().getWorkroomMapper().toEntity(workroom);
        var filter = new AddressFilterDto(country, region, city, street, home);
        List<AddressEntity> addressEntities = addressDao.findAllByWorkroomEntity(filter, workroomEntity);
        return addressEntities.stream().map(addressMapper::toDomain).toList();
    }

    @Override
    public List<Address> getAllByWorkroomWhere(String country, String region, String city, String street, String home) {
        AuthService authService = AuthServiceImpl.getInstance();
        Workroom curentWorkroom = authService.getCurentWorkroom();
        if (Objects.isNull(curentWorkroom)) {
            throw new AuthException("Щоб отримати всі відфільтровані адреси по відділу, спочатку аутентифікуйтесь");
        }
        return getAllByWorkroomWhere(curentWorkroom, country, region, city, street, home);
    }

    /**
     * Save the entity to the database table.
     *
     * @param address persistent entity
     * @return entity, with identifier of the last added record from the database
     */
    @Override
    public Address add(Address address) {
        if (!addressValidatorChain.validate(address)) {
            throw new DomainAddException("Не вдалось додати адресу із-за не валідних даних");
        }
        address.setId(null);
        var addressEntity = addressMapper.toEntity(address);
        addressEntity = addressDao.save(addressEntity);
        return addressMapper.toDomain(addressEntity);
    }

    /**
     * Update the domain to the database table.
     *
     * @param id ідентифікатор
     * @param address persistent entity
     */
    @Override
    public void set(UUID id, Address address) {
        if (!addressValidatorChain.validate(address)) {
            throw new DomainAddException("Не вдалось оновити адресу із-за не валідних даних");
        }

        address.setId(id);
        get(id);

        var addressEntity = addressMapper.toEntity(address);
        addressDao.save(addressEntity);
    }

    /**
     * Delete domain object from collection.
     *
     * @param id primary key identifier
     */
    @Override
    public void remove(UUID id) {
        get(id);
        addressDao.remove(id);
    }

    @Override
    public Map<String, List<String>> getValidationMessages() {
        return addressValidatorChain.getValidationMessages();
    }
}
