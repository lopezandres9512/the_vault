package com.hotel.app.service;

import com.hotel.app.model.*;
import com.hotel.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> obtenerTodos() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> obtenerPorId(Long id) {
        return hotelRepository.findById(id);
    }

    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public void eliminar(Long id) {
        hotelRepository.deleteById(id);
    }
}