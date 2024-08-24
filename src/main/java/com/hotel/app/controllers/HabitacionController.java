package com.hotel.app.controllers;

import com.hotel.app.model.Habitacion;
import com.hotel.app.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {
    @Autowired
    private HabitacionRepository repository;

    @GetMapping("/{id}")
    public Optional<Habitacion> getHabitacion(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public void createHabitacion(@RequestBody Habitacion h) {
        repository.save(h);
    }

    @GetMapping
    public Iterable<Habitacion> getHabitaciones() {
        return repository.findAll();
    }
}
