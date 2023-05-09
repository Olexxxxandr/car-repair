package com.olexxxxandr.mapper.impl;

import com.olexxxxandr.domain.impl.Client;
import com.olexxxxandr.entity.impl.ClientEntity;
import com.olexxxxandr.mapper.DomainMapper;

public class ClientMapper implements DomainMapper<ClientEntity, Client> {

    @Override
    public Client toDomain(ClientEntity entity) {
        Client client = Client.builder().phone(entity.getPhone()).email(entity.getEmail()).build();
        client.setId(entity.getId());
        client.setFirstName(entity.getFirstName());
        client.setLastName(entity.getLastName());
        client.setMiddleName(entity.getMiddleName());
        client.setPhoto(entity.getPhoto());
        client.setUpdatedAt(entity.getUpdatedAt());
        client.setCreatedAt(entity.getCreatedAt());
        return client;
    }

    @Override
    public ClientEntity toEntity(Client domain) {
        ClientEntity clientEntity =
                ClientEntity.builder()
                        .id(domain.getId())
                        .phone(domain.getPhone())
                        .email(domain.getEmail())
                        .build();
        clientEntity.setFirstName(domain.getFirstName());
        clientEntity.setLastName(domain.getLastName());
        clientEntity.setMiddleName(domain.getMiddleName());
        clientEntity.setPhoto(domain.getPhoto());
        clientEntity.setUpdatedAt(domain.getUpdatedAt());
        clientEntity.setCreatedAt(domain.getCreatedAt());
        return clientEntity;
    }

    private ClientMapper() {}

    private static class SingletonHolder {
        public static final ClientMapper INSTANCE = new ClientMapper();
    }

    public static ClientMapper getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
