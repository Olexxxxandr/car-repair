module com.olexxxxandr.carrapair.persistence {
    requires java.sql;
    requires java.desktop;
    requires org.slf4j;

    exports com.olexxxxandr.carrepair.persistence.entity.impl;
    exports com.olexxxxandr.carrepair.persistence.entity.proxy;
    exports com.olexxxxandr.carrepair.persistence.filter.impl;
    exports com.olexxxxandr.carrepair.persistence.dao;
    exports com.olexxxxandr.carrepair.persistence.filter;
    exports com.olexxxxandr.carrepair.persistence;
}
