package com.olexxxxandr;

import com.olexxxxandr.domain.impl.Client;
import com.olexxxxandr.factory.DaoFactory;
import com.olexxxxandr.repository.ClientRepository;
import com.olexxxxandr.repository.impl.ClientRepositoryImpl;

public class Main {

    public static void main(String[] args) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        ClientRepository clientRepository = ClientRepositoryImpl.getInstance(daoFactory.getClientDao());

        var client = Client.builder()
                .phone("111111")
                .email("testmail@gmail")
                .build();
        try {
            clientRepository.add(client);
        } catch(Exception e) {
              // TODO:
            System.out.println(e.getMessage());
        } finally {
            daoFactory.closeAllDaoConnections();
        }

        client.getValidationMessages().forEach((key, value) -> {
            System.out.println("Key: " + key);
            System.out.println("Values: " + value);
        });
    }
}
