package com.olexxxxandr.carrepair.domain;

import com.olexxxxandr.carrepair.persistence.DaoFactory;
import com.olexxxxandr.carrepair.persistence.entity.impl.AddressEntity;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DaoFactory daoFactory = DaoFactory.getInstance();

        try {
            List<AddressEntity> addressEntities = daoFactory.getAddressDao().findAll();
            addressEntities.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            daoFactory.closeAllDaoConnections();
        }
    }
}
