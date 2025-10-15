package com.hotel.app.controller;

import com.hotel.app.model.*;
import com.hotel.app.repository.HotelRepository;
import com.hotel.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/hoteles")
public class HotelController {
    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping
    public List<Hotel> obtenerTodos() {
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> obtenerPorId(@PathVariable Long id) {
        return hotelRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Hotel crearHotel(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHotel(@PathVariable Long id) {
        hotelRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
