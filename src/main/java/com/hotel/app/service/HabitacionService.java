package com.hotel.app.service;

import com.hotel.app.model.*;
import com.hotel.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class HabitacionService {
    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Habitacion> obtenerTodos() {
        return habitacionRepository.findAll();
    }

    public Optional<Habitacion> obtenerPorId(Long id) {
        return habitacionRepository.findById(id);
    }

    public Habitacion guardar(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public void eliminar(Long id) {
        habitacionRepository.deleteById(id);
    }
}
