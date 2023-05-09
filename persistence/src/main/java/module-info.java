module persistence {
    requires java.sql;
    requires java.desktop;
    requires org.slf4j;

    exports com.olexxxxandr.entity.impl;
    exports com.olexxxxandr.entity.proxy;
    exports com.olexxxxandr.filter.impl;
    exports com.olexxxxandr.dao;
    exports com.olexxxxandr.factory;
    exports com.olexxxxandr.filter;
}
