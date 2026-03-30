package com.hotel.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data-save")
public class DataRecoveryController {
    @Autowired
    private DataRecoveryService dataRecoveryService;

    @GetMapping
    public List<DataRecovery> getAll() {
        return dataRecoveryService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataRecovery> getById(@PathVariable Long id) {
        return dataRecoveryService
                .getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DataRecovery createDataRecovery(@RequestBody DataRecovery dataRecovery) {
        return dataRecoveryService.save(dataRecovery);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDataRecovery(@PathVariable Long id) {
        dataRecoveryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}