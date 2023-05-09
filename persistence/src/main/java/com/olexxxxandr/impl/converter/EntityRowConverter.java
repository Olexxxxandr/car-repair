package com.olexxxxandr.impl.converter;

import java.sql.ResultSet;

public interface EntityRowConverter<E> {

    E execute(ResultSet resultSet);
}
