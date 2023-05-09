package com.olexxxxandr.impl;

import static java.util.stream.Collectors.joining;

import com.olexxxxandr.dao.CarPhotoDao;
import com.olexxxxandr.entity.impl.CarPhotoEntity;
import com.olexxxxandr.exception.persistance.EntityNotFoundException;
import com.olexxxxandr.exception.persistance.PersistenceException;
import com.olexxxxandr.filter.impl.CarPhotoFilterDto;
import com.olexxxxandr.impl.converter.impl.CarPhotoEntityRowConverter;
import com.olexxxxandr.util.ConnectionManager;
import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarPhotoDaoImpl implements CarPhotoDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(CarPhotoDaoImpl.class);

    private static final String FIND_ALL_SQL =
            """
            SELECT id,
                   car_id,
                   photo,
                   description
              FROM car_photos
            """;

    private static final String FIND_BY_ID_SQL =
            FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private static final String SAVE_SQL =
            """
            INSERT INTO car_photos(id, car_id, photo, description)
            VALUES (?, ?);
            """;

    private static final String UPDATE_SQL =
            """
            UPDATE car_photos
               SET car_id = ?,
                   photo = ?,
                   description = ?
             WHERE id = ?;
            """;

    private static final String REMOVE_SQL =
            """
            DELETE FROM car_photos
                  WHERE id = ?;
            """;

    /**
     * Get an CarPhotoEntity object by identifier.
     *
     * @param id primary key identifier
     * @return Optional<CarPhotoEntity>
     */
    @Override
    public Optional<CarPhotoEntity> findOneById(UUID id) {
        try (var connection = ConnectionManager.get();
                var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setString(1, id.toString());
            var resultSet = statement.executeQuery();
            CarPhotoEntity carPhotoEntity = null;
            if (resultSet.next()) {
                carPhotoEntity = CarPhotoEntityRowConverter.getInstance().execute(resultSet);

                LOGGER.info(
                        "Founded entity %s by id = %s. Запит: %s"
                                .formatted(carPhotoEntity, id.toString(), FIND_BY_ID_SQL));
            }
            return Optional.ofNullable(carPhotoEntity);
        } catch (SQLException e) {
            String message =
                    "При знаходженні запису по id в %s. Детально: %s"
                            .formatted(CarPhotoDaoImpl.class.getSimpleName(), e.getMessage());
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
    public List<CarPhotoEntity> findAll() {
        try (var connection = ConnectionManager.get();
                var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = statement.executeQuery();
            var carPhotoEntities = new ArrayList<CarPhotoEntity>(resultSet.getFetchSize());
            while (resultSet.next()) {
                carPhotoEntities.add(CarPhotoEntityRowConverter.getInstance().execute(resultSet));
            }

            LOGGER.info(
                    "Кількість сутностей: {}. Запит: {}", carPhotoEntities.size(), FIND_ALL_SQL);
            return carPhotoEntities;
        } catch (SQLException e) {
            String message =
                    "При знаходженні всіх записів в %s. Детально: %s"
                            .formatted(CarPhotoDaoImpl.class.getSimpleName(), e.getMessage());
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
    public List<CarPhotoEntity> findAll(CarPhotoFilterDto filter) {
        var parameters = new ArrayList<>();
        var whereSQL = new ArrayList<String>();

        if (Objects.nonNull(filter.carEntity())) {
            whereSQL.add("car_id = ?");
            parameters.add(filter.carEntity().getId());
        }

        var where = whereSQL.stream().collect(joining(" AND ", " WHERE ", ""));
        var sql = FIND_ALL_SQL + where;

        try (var connection = ConnectionManager.get();
                var statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }
            var resultSet = statement.executeQuery();
            var carPhotoEntities = new ArrayList<CarPhotoEntity>(resultSet.getFetchSize());
            while (resultSet.next()) {
                carPhotoEntities.add(CarPhotoEntityRowConverter.getInstance().execute(resultSet));
            }

            LOGGER.info(
                    "Кількість сутностей після фільтрації: {}. Запит: {}",
                    carPhotoEntities.size(),
                    sql);
            return carPhotoEntities;
        } catch (SQLException e) {
            String message =
                    "При знаходженні всіх записів за фільтром %s в %s. Детально: %s"
                            .formatted(
                                    where, CarPhotoDaoImpl.class.getSimpleName(), e.getMessage());
            LOGGER.error(message);
            throw new PersistenceException(message);
        }
    }

    /**
     * Save or update the CarPhoto to the database table.
     *
     * @param carPhotoEntity persistent CarPhoto
     * @return CarPhoto, with identifier of the last added record from the database
     */
    @Override
    public CarPhotoEntity save(CarPhotoEntity carPhotoEntity) {
        boolean isNullId = Objects.isNull(carPhotoEntity.getId());
        try (var connection = ConnectionManager.get();
                var statement = connection.prepareStatement(isNullId ? SAVE_SQL : UPDATE_SQL)) {
            if (isNullId) {
                var id = UUID.randomUUID();
                carPhotoEntity.setId(id);
                statement.setString(1, id.toString());
                statement.setString(2, carPhotoEntity.getCarEntity().getId().toString());
                statement.setBlob(3, new ByteArrayInputStream(carPhotoEntity.getPhoto()));
                statement.setString(4, carPhotoEntity.getDescription());

                LOGGER.info("Формування запиту на збереження.");
            } else {
                statement.setString(1, carPhotoEntity.getCarEntity().getId().toString());
                statement.setBlob(2, new ByteArrayInputStream(carPhotoEntity.getPhoto()));
                statement.setString(3, carPhotoEntity.getDescription());
                statement.setString(4, carPhotoEntity.getId().toString());

                LOGGER.info("Формування запиту на оновлення.");
            }
            statement.executeUpdate();

            LOGGER.info("Успішне оновлення або збереження об'єкту: {}", carPhotoEntity);
            return carPhotoEntity;
        } catch (SQLException e) {
            String message =
                    "При збереженні або оновленні запису в %s. Детально: %s"
                            .formatted(CarPhotoDaoImpl.class.getSimpleName(), e.getMessage());
            LOGGER.error(message);
            throw new PersistenceException(message);
        }
    }

    /**
     * Delete a CarPhoto from the database table by identifier.
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
                            .formatted(CarPhotoDaoImpl.class.getSimpleName(), e.getMessage());
            LOGGER.error(message);
            throw new PersistenceException(message);
        }
    }

    private CarPhotoDaoImpl() {}

    private static class SingletonHolder {
        public static final CarPhotoDaoImpl INSTANCE = new CarPhotoDaoImpl();
    }

    public static CarPhotoDaoImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
