package com.hotel.app.controller;

import com.hotel.app.model.*;
import com.hotel.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/hoteles")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping
    public List<Hotel> obtenerTodos() {
        return hotelService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> obtenerPorId(@PathVariable Long id) {
        return hotelService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Hotel crearHotel(@RequestBody Hotel hotel) {
        return hotelService.guardar(hotel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHotel(@PathVariable Long id) {
        hotelService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}