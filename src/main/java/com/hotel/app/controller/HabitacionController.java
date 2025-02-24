package com.hotel.app.controller;

import com.hotel.app.model.*;
import com.hotel.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {
    @Autowired
    private HabitacionService habitacionService;

    @GetMapping
    public List<Habitacion> obtenerTodos() {
        return habitacionService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> obtenerPorId(@PathVariable Long id) {
        return habitacionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Habitacion crearHabitacion(@RequestBody Habitacion habitacion) {
        return habitacionService.guardar(habitacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHabitacion(@PathVariable Long id) {
        habitacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}