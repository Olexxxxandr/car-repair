module com.olexxxxandr.carrepair.domain {
    requires com.olexxxxandr.carrapair.persistence;
    requires java.desktop;
    requires jbcrypt;

    exports com.olexxxxandr.carrepair.domain.impl;
    exports com.olexxxxandr.carrepair.domain.proxy;
    exports com.olexxxxandr.carrepair.domain.repository;
    exports com.olexxxxandr.carrepair.domain.factory;
    exports com.olexxxxandr.carrepair.domain.service;
    exports com.olexxxxandr.carrepair.domain.service.impl;
}
