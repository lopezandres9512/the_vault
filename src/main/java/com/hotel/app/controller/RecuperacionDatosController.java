package com.hotel.app.controller;

import com.hotel.app.model.*;
import com.hotel.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/recuperacion-datos")
public class RecuperacionDatosController {
    @Autowired
    private RecuperacionDatosService recuperacionDatosService;

    @GetMapping
    public List<RecuperacionDatos> obtenerTodos() {
        return recuperacionDatosService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecuperacionDatos> obtenerPorId(@PathVariable Long id) {
        return recuperacionDatosService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public RecuperacionDatos crearRecuperacionDatos(@RequestBody RecuperacionDatos recuperacionDatos) {
        return recuperacionDatosService.guardar(recuperacionDatos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRecuperacionDatos(@PathVariable Long id) {
        recuperacionDatosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}