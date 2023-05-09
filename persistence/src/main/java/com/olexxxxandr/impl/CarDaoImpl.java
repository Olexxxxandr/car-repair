package com.olexxxxandr.impl;

import static java.util.stream.Collectors.joining;

import com.olexxxxandr.dao.CarDao;
import com.olexxxxandr.entity.impl.CarEntity;
import com.olexxxxandr.exception.persistance.EntityNotFoundException;
import com.olexxxxandr.exception.persistance.PersistenceException;
import com.olexxxxandr.filter.impl.CarFilterDto;
import com.olexxxxandr.impl.converter.impl.CarEntityRowConverter;
import com.olexxxxandr.util.ConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarDaoImpl implements CarDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(CarDaoImpl.class);

    private static final String FIND_ALL_SQL =
            """
            SELECT id,
                   model_id,
                   number,
                   year,
                   engine_type,
                   mileage,
                   color,
                   updated_at,
                   created_at
              FROM cars
            """;

    private static final String FIND_BY_ID_SQL =
            FIND_ALL_SQL + """
            WHERE id = ?
            """;
    private static final String SAVE_SQL =
            """
            INSERT INTO cars(id, model_id, number, year, engine_type, mileage, color, updated_at, created_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
            """;
    private static final String UPDATE_SQL =
            """
            UPDATE cars
               SET model_id = ?,
                   number = ?,
                   year = ?,
                   engine_type = ?,
                   mileage = ?,
                   color = ?,
                   updated_at = ?,
                   created_at = ?
             WHERE id = ?;
            """;

    private static final String REMOVE_SQL =
            """
            DELETE FROM cars
                  WHERE id = ?;
            """;

    /**
     * Get an CarEntity object by identifier.
     *
     * @param id primary key identifier
     * @return Optional<CarEntity>
     */
    @Override
    public Optional<CarEntity> findOneById(UUID id) {
        try (var connection = ConnectionManager.get()) {
            return findOneById(id, connection);
        } catch (SQLException e) {
            String message =
                    "При знаходженні запису по id в %s. Детально: %s"
                            .formatted(CarDaoImpl.class.getSimpleName(), e.getMessage());
            LOGGER.error(message);
            throw new EntityNotFoundException(message);
        }
    }

    /**
     * Get an entity object by identifier with connection.
     *
     * @param id primary key identifier
     * @param connection JDBC Connection
     * @return Optional<CarEntity>
     */
    @Override
    public Optional<CarEntity> findOneById(UUID id, Connection connection) {
        try (var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setString(1, id.toString());
            var resultSet = statement.executeQuery();
            CarEntity carEntity = null;
            if (resultSet.next()) {
                carEntity = CarEntityRowConverter.getInstance().execute(resultSet);

                LOGGER.info(
                        "Founded entity %s by id = %s. Запит: %s"
                                .formatted(carEntity, id.toString(), FIND_BY_ID_SQL));
            }
            return Optional.ofNullable(carEntity);
        } catch (SQLException e) {
            String message =
                    "При знаходженні запису по id в %s. Детально: %s"
                            .formatted(CarDaoImpl.class.getSimpleName(), e.getMessage());
            LOGGER.error(message);
            throw new EntityNotFoundException(message);
        }
    }

    /**
     * Get the entire collection of entities.
     *
     * @return collection of Entities
     */
    @Override
    public List<CarEntity> findAll() {
        try (var connection = ConnectionManager.get();
                var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = statement.executeQuery();
            var carEntities = new ArrayList<CarEntity>(resultSet.getFetchSize());
            while (resultSet.next()) {
                carEntities.add(CarEntityRowConverter.getInstance().execute(resultSet));
            }

            LOGGER.info("Кількість сутностей: {}. Запит: {}", carEntities.size(), FIND_ALL_SQL);
            return carEntities;
        } catch (SQLException e) {
            String message =
                    "При знаходженні всіх записів в %s. Детально: %s"
                            .formatted(CarDaoImpl.class.getSimpleName(), e.getMessage());
            LOGGER.error(message);
            throw new PersistenceException(message);
        }
    }

    /**
     * Get the entire collection of entities by filtering in RDMS.
     *
     * @param filter values for where conditions
     * @return collection of Entities
     */
    @Override
    public List<CarEntity> findAll(CarFilterDto filter) {
        var parameters = new ArrayList<>();
        var whereSQL = new ArrayList<String>();

        if (Objects.nonNull(filter.modelEntity())) {
            whereSQL.add("model_id = ?");
            parameters.add(filter.modelEntity().getId());
        }
        if (Objects.nonNull(filter.number())) {
            whereSQL.add("number LIKE ?");
            parameters.add("%" + filter.number() + "%");
        }
        if (filter.year() != 0) {
            whereSQL.add("year = ?");
            parameters.add(filter.year());
        }
        if (Objects.nonNull(filter.engineType())) {
            whereSQL.add("engine_type = ?");
            parameters.add(filter.engineType());
        }
        if (filter.mileage() != 0) {
            whereSQL.add("mileage = ?");
            parameters.add(filter.mileage());
        }
        if (Objects.nonNull(filter.color())) {
            whereSQL.add("color = ?");
            parameters.add(filter.color());
        }
        if (Objects.nonNull(filter.updatedAt())) {
            whereSQL.add("updated_at = ?");
            parameters.add(filter.updatedAt());
        }
        if (Objects.nonNull(filter.createdAt())) {
            whereSQL.add("created_at = ?");
            parameters.add(filter.createdAt());
        }

        var where = whereSQL.stream().collect(joining(" AND ", " WHERE ", ""));
        var sql = FIND_ALL_SQL + where;

        try (var connection = ConnectionManager.get();
                var statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }
            var resultSet = statement.executeQuery();
            var carEntities = new ArrayList<CarEntity>(resultSet.getFetchSize());
            while (resultSet.next()) {
                carEntities.add(CarEntityRowConverter.getInstance().execute(resultSet));
            }

            LOGGER.info(
                    "Кількість сутностей після фільтрації: {}. Запит: {}", carEntities.size(), sql);
            return carEntities;
        } catch (SQLException e) {
            String message =
                    "При знаходженні всіх записів за фільтром %s в %s. Детально: %s"
                            .formatted(where, CarDaoImpl.class.getSimpleName(), e.getMessage());
            LOGGER.error(message);
            throw new PersistenceException(message);
        }
    }

    /**
     * Save or update the car to the database table.
     *
     * @param carEntity persistent car
     * @return carEntity, with identifier of the last added record from the database
     */
    @Override
    public CarEntity save(CarEntity carEntity) {
        boolean isNullId = Objects.isNull(carEntity.getId());
        try (var connection = ConnectionManager.get();
                var statement = connection.prepareStatement(isNullId ? SAVE_SQL : UPDATE_SQL)) {
            if (isNullId) {
                var id = UUID.randomUUID();
                carEntity.setId(id);
                statement.setString(1, id.toString());
                statement.setInt(2, carEntity.getModelEntity().getId());
                statement.setString(3, carEntity.getNumber());
                statement.setShort(4, carEntity.getYear());
                statement.setString(5, carEntity.getEngineType().getName());
                statement.setInt(6, carEntity.getMileage());
                statement.setString(7, carEntity.toHexColorString());
                statement.setTimestamp(8, Timestamp.valueOf(carEntity.getUpdatedAt()));
                statement.setTimestamp(9, Timestamp.valueOf(carEntity.getCreatedAt()));

                LOGGER.info("Формування запиту на збереження.");
            } else {
                statement.setInt(1, carEntity.getModelEntity().getId());
                statement.setString(2, carEntity.getNumber());
                statement.setShort(3, carEntity.getYear());
                statement.setString(4, carEntity.getEngineType().getName());
                statement.setInt(5, carEntity.getMileage());
                statement.setString(6, carEntity.toHexColorString());
                statement.setTimestamp(7, Timestamp.valueOf(carEntity.getUpdatedAt()));
                statement.setTimestamp(8, Timestamp.valueOf(carEntity.getCreatedAt()));
                statement.setString(9, carEntity.getId().toString());

                LOGGER.info("Формування запиту на оновлення.");
            }
            statement.executeUpdate();

            LOGGER.info("Успішне оновлення або збереження об'єкту: {}", carEntity);
            return carEntity;
        } catch (SQLException e) {
            String message =
                    "При збереженні або оновленні запису в %s. Детально: %s"
                            .formatted(CarDaoImpl.class.getSimpleName(), e.getMessage());
            LOGGER.error(message);
            throw new PersistenceException(message);
        }
    }

    /**
     * Delete a car from the database table by identifier.
     *
     * @param id primary key identifier
     */
    @Override
    public void remove(UUID id) {
        try (var connection = ConnectionManager.get();
                var statement = connection.prepareStatement(REMOVE_SQL)) {
            statement.setString(1, id.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            String message =
                    "Помилка при операції видалення рядка таблиці в %s. Детально: %s"
                            .formatted(CarDaoImpl.class.getSimpleName(), e.getMessage());
            LOGGER.error(message);
            throw new PersistenceException(message);
        }
    }

    private CarDaoImpl() {}

    private static class SingletonHolder {
        public static final CarDaoImpl INSTANCE = new CarDaoImpl();
    }

    public static CarDaoImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
