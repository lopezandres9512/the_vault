package com.hotel.app.controller;

import com.hotel.app.model.*;
import com.hotel.app.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping
    public List<Habitacion> getAll() {
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getById(@PathVariable Long id) {
        return habitacionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return habitacionRepository.save(habitacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHabitacion(@PathVariable Long id) {
        habitacionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}