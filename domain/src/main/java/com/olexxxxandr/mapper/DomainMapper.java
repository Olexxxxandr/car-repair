package com.olexxxxandr.mapper;

public interface DomainMapper<E, D> {
    D toDomain(E entity);

    E toEntity(D domain);
}
