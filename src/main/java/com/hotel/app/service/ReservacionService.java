package com.hotel.app.service;

import com.hotel.app.model.*;
import com.hotel.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ReservacionService {
    @Autowired
    private ReservacionRepository reservacionRepository;

    public List<Reservacion> obtenerTodos() {
        return reservacionRepository.findAll();
    }

    public Optional<Reservacion> obtenerPorId(Long id) {
        return reservacionRepository.findById(id);
    }

    public Reservacion guardar(Reservacion reservacion) {
        return reservacionRepository.save(reservacion);
    }

    public void eliminar(Long id) {
        reservacionRepository.deleteById(id);
    }
}