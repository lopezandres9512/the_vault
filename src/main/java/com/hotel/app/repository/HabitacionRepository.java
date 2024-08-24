package com.hotel.app.repository;

import com.hotel.app.model.Habitacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepository extends CrudRepository<Habitacion, Long> {
}
