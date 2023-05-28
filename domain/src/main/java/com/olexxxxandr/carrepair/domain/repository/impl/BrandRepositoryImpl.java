package com.olexxxxandr.carrepair.domain.repository.impl;

import com.olexxxxandr.carrepair.domain.exception.DomainAddException;
import com.olexxxxandr.carrepair.domain.exception.DomainNotFoundException;
import com.olexxxxandr.carrepair.domain.impl.Brand;
import com.olexxxxandr.carrepair.domain.mapper.BrandMapper;
import com.olexxxxandr.carrepair.domain.mapper.MapperFactory;
import com.olexxxxandr.carrepair.domain.repository.BrandRepository;
import com.olexxxxandr.carrepair.domain.validator.brand.BrandValidatorChain;
import com.olexxxxandr.carrepair.persistence.DaoFactory;
import com.olexxxxandr.carrepair.persistence.dao.BrandDao;
import com.olexxxxandr.carrepair.persistence.entity.impl.BrandEntity;
import com.olexxxxandr.carrepair.persistence.filter.impl.BrandFilterDto;
import java.util.List;
import java.util.Map;

public final class BrandRepositoryImpl implements BrandRepository {

    private final BrandDao brandDao;
    private final BrandMapper brandMapper;
    private final BrandValidatorChain brandValidatorChain;

    public BrandRepositoryImpl() {
        this.brandDao = DaoFactory.getInstance().getBrandDao();
        this.brandMapper = MapperFactory.getInstance().getBrandMapper();
        this.brandValidatorChain = BrandValidatorChain.getInstance();
    }

    /**
     * Get the entire collection of entities by filtering in RDMS.
     *
     * @param name
     * @return collection of Domains
     */
    @Override
    public List<Brand> getAllWhere(String name) {
        var filter = new BrandFilterDto(name);
        List<BrandEntity> brandEntities = brandDao.findAll(filter);
        return brandEntities.stream().map(brandMapper::toDomain).toList();
    }

    /**
     * Get a domain object by identifier.
     *
     * @param id primary key identifier
     * @return D domain object
     */
    @Override
    public Brand get(Integer id) {
        BrandEntity brandEntity = brandDao.findOneById(id)
                .orElseThrow(() -> new DomainNotFoundException("Не вдалось знайти бренд по ідентифікатору"));
        return brandMapper.toDomain(brandEntity);
    }

    /**
     * Get the entire collection of entities.
     *
     * @return collection of Entities
     */
    @Override
    public List<Brand> getAll() {
        List<BrandEntity> brandEntities = brandDao.findAll();
        return brandEntities.stream().map(brandMapper::toDomain).toList();
    }

    /**
     * Save the entity to the database table.
     *
     * @param brand persistent entity
     * @return entity, with identifier of the last added record from the database
     */
    @Override
    public Brand add(Brand brand) {
        if (!brandValidatorChain.validate(brand)) {
            throw new DomainAddException("Не вдалось додати бренд із-за не валідних даних");
        }
        var brandEntity = brandMapper.toEntity(brand);
        brandEntity.setId(null);
        brandEntity = brandDao.save(brandEntity);
        return brandMapper.toDomain(brandEntity);
    }

    /**
     * Update the domain to the database table.
     *
     * @param id
     * @param brand persistent entity
     */
    @Override
    public void set(Integer id, Brand brand) {
        if (!brandValidatorChain.validate(brand)) {
            throw new DomainAddException("Не вдалось оновити бренд із-за не валідних даних");
        }

        brand.setId(id);
        get(id);

        var brandEntity = brandMapper.toEntity(brand);
        brandDao.save(brandEntity);
    }

    /**
     * Delete domain object from collection.
     *
     * @param id primary key identifier
     */
    @Override
    public void remove(Integer id) {
        get(id);
        brandDao.remove(id);
    }

    @Override
    public Map<String, List<String>> getValidationMessages() {
        return brandValidatorChain.getValidationMessages();
    }
}
