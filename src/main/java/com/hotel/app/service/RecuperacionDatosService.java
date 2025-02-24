package com.hotel.app.service;

import com.hotel.app.model.*;
import com.hotel.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RecuperacionDatosService {
    @Autowired
    private RecuperacionDatosRepository recuperacionDatosRepository;

    public List<RecuperacionDatos> obtenerTodos() {
        return recuperacionDatosRepository.findAll();
    }

    public Optional<RecuperacionDatos> obtenerPorId(Long id) {
        return recuperacionDatosRepository.findById(id);
    }

    public RecuperacionDatos guardar(RecuperacionDatos recuperacionDatos) {
        return recuperacionDatosRepository.save(recuperacionDatos);
    }

    public void eliminar(Long id) {
        recuperacionDatosRepository.deleteById(id);
    }
}
