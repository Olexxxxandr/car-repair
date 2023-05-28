package com.olexxxxandr.carrepair.presentation.mapper;

public interface ModelMapper<D, M> {
    M toModel(D domain);

    D toDomain(M model);
}
