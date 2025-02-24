package com.hotel.app.controller;

import com.hotel.app.model.*;
import com.hotel.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/reservaciones")
public class ReservacionController {
    @Autowired
    private ReservacionService reservacionService;

    @GetMapping
    public List<Reservacion> obtenerTodos() {
        return reservacionService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservacion> obtenerPorId(@PathVariable Long id) {
        return reservacionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Reservacion crearReservacion(@RequestBody Reservacion reservacion) {
        return reservacionService.guardar(reservacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReservacion(@PathVariable Long id) {
        reservacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}