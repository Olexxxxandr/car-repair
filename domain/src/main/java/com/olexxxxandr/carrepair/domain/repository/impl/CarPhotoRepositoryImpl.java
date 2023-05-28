package com.olexxxxandr.carrepair.domain.repository.impl;

import com.olexxxxandr.carrepair.domain.exception.AuthException;
import com.olexxxxandr.carrepair.domain.exception.DomainAddException;
import com.olexxxxandr.carrepair.domain.exception.DomainNotFoundException;
import com.olexxxxandr.carrepair.domain.impl.Car;
import com.olexxxxandr.carrepair.domain.impl.CarPhoto;
import com.olexxxxandr.carrepair.domain.impl.Workroom;
import com.olexxxxandr.carrepair.domain.mapper.CarPhotoMapper;
import com.olexxxxandr.carrepair.domain.mapper.MapperFactory;
import com.olexxxxandr.carrepair.domain.repository.CarPhotoRepository;
import com.olexxxxandr.carrepair.domain.service.AuthService;
import com.olexxxxandr.carrepair.domain.service.impl.AuthServiceImpl;
import com.olexxxxandr.carrepair.domain.validator.carphoto.CarPhotoValidatorChain;
import com.olexxxxandr.carrepair.persistence.DaoFactory;
import com.olexxxxandr.carrepair.persistence.dao.CarPhotoDao;
import com.olexxxxandr.carrepair.persistence.entity.impl.CarPhotoEntity;
import com.olexxxxandr.carrepair.persistence.entity.impl.WorkroomEntity;
import com.olexxxxandr.carrepair.persistence.filter.impl.CarPhotoFilterDto;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class CarPhotoRepositoryImpl implements CarPhotoRepository {
    private final CarPhotoDao carPhotoDao;
    private final CarPhotoMapper carPhotoMapper;
    private final CarPhotoValidatorChain carPhotoValidatorChain;

    public CarPhotoRepositoryImpl() {
        carPhotoDao = DaoFactory.getInstance().getCarPhotoDao();
        carPhotoMapper = MapperFactory.getInstance().getCarPhotoMapper();
        carPhotoValidatorChain = CarPhotoValidatorChain.getInstance();
    }

    /**
     * Get the entire collection of entities by filtering in RDMS.
     *
     * @return collection of Domains
     */
    @Override
    public List<CarPhoto> getAllWhere(Car car) {
        var carMapper = MapperFactory.getInstance().getCarMapper();
        var filter = new CarPhotoFilterDto(Objects.nonNull(car) ? carMapper.toEntity(car) : null);
        List<CarPhotoEntity> carPhotoEntities = carPhotoDao.findAll(filter);
        return carPhotoEntities.stream().map(carPhotoMapper::toDomain).toList();
    }

    /**
     * Get a domain object by identifier.
     *
     * @param id primary key identifier
     * @return D domain object
     */
    @Override
    public CarPhoto get(UUID id) {
        CarPhotoEntity carPhotoEntity = carPhotoDao
                .findOneById(id)
                .orElseThrow(
                        () -> new DomainNotFoundException("Не вдалось знайти фото автомобіля по" + " ідентифікатору"));
        return carPhotoMapper.toDomain(carPhotoEntity);
    }

    /**
     * Get the entire collection of entities.
     *
     * @return collection of Entities
     */
    @Override
    public List<CarPhoto> getAll() {
        List<CarPhotoEntity> carPhotoEntities = carPhotoDao.findAll();
        return carPhotoEntities.stream().map(carPhotoMapper::toDomain).toList();
    }

    @Override
    public List<CarPhoto> getAllByWorkroom(Workroom workroom) {
        WorkroomEntity workroomEntity =
                MapperFactory.getInstance().getWorkroomMapper().toEntity(workroom);
        List<CarPhotoEntity> carPhotoEntities = carPhotoDao.findAllByWorkroomEntity(workroomEntity);
        return carPhotoEntities.stream().map(carPhotoMapper::toDomain).toList();
    }

    @Override
    public List<CarPhoto> getAllByWorkroom() {
        AuthService authService = AuthServiceImpl.getInstance();
        Workroom curentWorkroom = authService.getCurentWorkroom();
        if (Objects.isNull(curentWorkroom)) {
            throw new AuthException("Щоб отримати всі фотографії автомобіля по відділу, спочатку аутентифікуйтесь");
        }
        return getAllByWorkroom(curentWorkroom);
    }

    @Override
    public List<CarPhoto> getAllByWorkroomWhere(Workroom workroom, Car car) {
        var workroomMapper = MapperFactory.getInstance().getWorkroomMapper();
        var carMapper = MapperFactory.getInstance().getCarMapper();
        var filter = new CarPhotoFilterDto(Objects.nonNull(car) ? carMapper.toEntity(car) : null);
        List<CarPhotoEntity> carPhotoEntities = carPhotoDao.findAllByWorkroomEntity(
                filter, Objects.nonNull(workroom) ? workroomMapper.toEntity(workroom) : null);
        return carPhotoEntities.stream().map(carPhotoMapper::toDomain).toList();
    }

    @Override
    public List<CarPhoto> getAllByWorkroomWhere(Car car) {
        AuthService authService = AuthServiceImpl.getInstance();
        Workroom curentWorkroom = authService.getCurentWorkroom();
        if (Objects.isNull(curentWorkroom)) {
            throw new AuthException("Щоб отримати всі фотографії автомобіля по відділу, спочатку аутентифікуйтесь");
        }
        return getAllByWorkroomWhere(curentWorkroom, car);
    }

    /**
     * Save the entity to the database table.
     *
     * @param carPhoto persistent entity
     * @return entity, with identifier of the last added record from the database
     */
    @Override
    public CarPhoto add(CarPhoto carPhoto) {
        if (!carPhotoValidatorChain.validate(carPhoto)) {
            throw new DomainAddException("Не вдалось додати фото автомобіля із-за не валідних даних");
        }
        carPhoto.setId(null);
        var carPhotoEntity = carPhotoMapper.toEntity(carPhoto);
        carPhotoEntity = carPhotoDao.save(carPhotoEntity);
        return carPhotoMapper.toDomain(carPhotoEntity);
    }

    /**
     * Update the domain to the database table.
     *
     * @param id ідентифікатор
     * @param carPhoto persistent entity
     */
    @Override
    public void set(UUID id, CarPhoto carPhoto) {
        if (!carPhotoValidatorChain.validate(carPhoto)) {
            throw new DomainAddException("Не вдалось оновити фото автомобіля із-за не валідних даних");
        }
        carPhoto.setId(id);
        get(id);

        var carPhotoEntity = carPhotoMapper.toEntity(carPhoto);
        carPhotoDao.save(carPhotoEntity);
    }

    /**
     * Delete domain object from collection.
     *
     * @param id primary key identifier
     */
    @Override
    public void remove(UUID id) {
        get(id);
        carPhotoDao.remove(id);
    }

    @Override
    public Map<String, List<String>> getValidationMessages() {
        return carPhotoValidatorChain.getValidationMessages();
    }
}
