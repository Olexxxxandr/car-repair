package com.olexxxxandr.repository.impl;

import com.olexxxxandr.dao.ClientDao;
import com.olexxxxandr.domain.impl.Client;
import com.olexxxxandr.domain.validator.ClientValidateHandler;
import com.olexxxxandr.domain.validator.client.EmailClientValidateHandler;
import com.olexxxxandr.domain.validator.client.PhoneClientValidateHandler;
import com.olexxxxandr.domain.validator.client.name.FirstNameClientValidateHandler;
import com.olexxxxandr.domain.validator.client.name.LastNameClientValidateHandler;
import com.olexxxxandr.domain.validator.client.name.MiddleNameClientValidateHandler;
import com.olexxxxandr.domainexception.DomainAddException;
import com.olexxxxandr.entity.impl.ClientEntity;
import com.olexxxxandr.domainexception.DomainNotFoundException;
import com.olexxxxandr.filter.impl.ClientFilterDto;
import com.olexxxxandr.mapper.impl.ClientMapper;
import com.olexxxxandr.repository.ClientRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ClientRepositoryImpl implements ClientRepository {

    private final ClientDao clientDao;
    private final ClientMapper clientMapper;

    private ClientRepositoryImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
        this.clientMapper = ClientMapper.getInstance();
    }

    /**
     * Get a domain object by identifier.
     *
     * @param id primary key identifier
     * @return D domain object
     */
    @Override
    public Client get(UUID id) {
        ClientEntity clientEntity =
                clientDao
                        .findOneById(id)
                        .orElseThrow(
                                () ->
                                        new DomainNotFoundException(
                                                "Не вдалось знайти клієнта по ідентифікатору."));
        return clientMapper.toDomain(clientEntity);
    }

    /**
     * Get the entire collection of entities.
     *
     * @return collection of Entities
     */
    @Override
    public List<Client> getAll() {
        List<ClientEntity> clientEntities = clientDao.findAll();
        return clientEntities.stream().map(clientMapper::toDomain).toList();
    }

    /**
     * Get the entire collection of entities by filtering in RDMS.
     *
     * @param filter values for where conditions
     * @return collection of Entities
     */
    @Override
    public List<Client> getAll(ClientFilterDto filter) {
        List<ClientEntity> clientEntities = clientDao.findAll(filter);
        return clientEntities.stream().map(clientMapper::toDomain).toList();
    }

    /**
     * Save the entity to the database table.
     *
     * @param client persistent entity
     * @return entity, with identifier of the last added record from the database
     */
    @Override
    public Client add(Client client) {
        validate(client);

        if(!client.isValid()) {
            throw new DomainAddException("Не вдалось додати клієнта із-за не валідних даних");
        }

        client.setId(null);
        LocalDateTime now = LocalDateTime.now();
        client.setUpdatedAt(now);
        client.setCreatedAt(now);
        ClientEntity clientEntity = clientMapper.toEntity(client);
        clientEntity = clientDao.save(clientEntity);
        return clientMapper.toDomain(clientEntity);
    }

    /**
     * Update the client to the database table.
     *
     * @param id the identifier by which we want to update
     * @param client persistent entity
     * @return entity, with identifier of the last added record from the database
     */
    @Override
    public Client set(UUID id, Client client) {
        validate(client);
        client.setId(id);
        client.setUpdatedAt(LocalDateTime.now());
        clientDao
                .findOneById(id)
                .orElseThrow(
                        () ->
                                new DomainNotFoundException(
                                        "Не вдалось знайти клієнта по ідентифікатору для"
                                                + " оновлення."));

        ClientEntity clientEntity = clientMapper.toEntity(client);
        clientEntity = clientDao.save(clientEntity);
        return clientMapper.toDomain(clientEntity);
    }

    /**
     * Delete domain object from collection.
     *
     * @param id primary key identifier
     */
    @Override
    public void remove(UUID id) {
        clientDao
                .findOneById(id)
                .orElseThrow(
                        () ->
                                new DomainNotFoundException(
                                        "Не вдалось знайти клієнта по ідентифікатору для"
                                                + " видалення."));
        clientDao.remove(id);
    }

    /**
     * Client validate by CoR design pattern
     * @param client client
     */
    private void validate(Client client) {
        ClientValidateHandler phoneClientValidateHandler = PhoneClientValidateHandler.getInstance();
        ClientValidateHandler emailClientValidateHandler = EmailClientValidateHandler.getInstance();
        ClientValidateHandler firstNameClientValidateHandler = FirstNameClientValidateHandler.getInstance();
        ClientValidateHandler lastNameClientValidateHandler = LastNameClientValidateHandler.getInstance();
        ClientValidateHandler middleNameClientValidateHandler = MiddleNameClientValidateHandler.getInstance();

        phoneClientValidateHandler.setNext(emailClientValidateHandler);
        emailClientValidateHandler.setNext(firstNameClientValidateHandler);
        firstNameClientValidateHandler.setNext(lastNameClientValidateHandler);
        lastNameClientValidateHandler.setNext(middleNameClientValidateHandler);

        phoneClientValidateHandler.validate(client);
    }

    private static volatile ClientRepositoryImpl instance;

    public static ClientRepositoryImpl getInstance(ClientDao clientDao) {
        if (instance == null) {
            synchronized (ClientRepositoryImpl.class) {
                if (instance == null) {
                    instance = new ClientRepositoryImpl(clientDao);
                }
            }
        }

        return instance;
    }
}
