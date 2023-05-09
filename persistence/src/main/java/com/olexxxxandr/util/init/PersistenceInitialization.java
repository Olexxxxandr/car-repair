package com.olexxxxandr.util.init;

import com.olexxxxandr.util.ConnectionManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.stream.Collectors;

public final class PersistenceInitialization {

    /** Start the database migration. Create a schema for car_repair. */
    public static void run() {
        try (Connection connection = ConnectionManager.get();
                Statement statement = connection.createStatement()) {
            statement.execute(getSQL(Path.of("db", "migrations", "ddl.sql").toString()));
            statement.executeUpdate(getSQL(Path.of("db", "migrations", "dml.sql").toString()));
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    private static String getSQL(final String resourceName) {
        return new BufferedReader(
                        new InputStreamReader(
                                Objects.requireNonNull(
                                        ConnectionManager.class
                                                .getClassLoader()
                                                .getResourceAsStream(resourceName))))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    private PersistenceInitialization() {}
}
