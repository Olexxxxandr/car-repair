package com.olexxxxandr.carrepair.domain.repository;

import com.olexxxxandr.carrepair.domain.impl.Car;
import com.olexxxxandr.carrepair.domain.impl.CarPhoto;
import com.olexxxxandr.carrepair.domain.impl.Workroom;
import java.util.List;
import java.util.UUID;

public interface CarPhotoRepository extends GenericRepository<UUID, CarPhoto> {

    /**
     * Get the entire collection of entities by filtering in RDMS.
     *
     * @return collection of Domains
     */
    List<CarPhoto> getAllWhere(Car car);

    List<CarPhoto> getAllByWorkroom(Workroom workroom);

    List<CarPhoto> getAllByWorkroom();

    List<CarPhoto> getAllByWorkroomWhere(Workroom workroom, Car car);

    List<CarPhoto> getAllByWorkroomWhere(Car car);
}
