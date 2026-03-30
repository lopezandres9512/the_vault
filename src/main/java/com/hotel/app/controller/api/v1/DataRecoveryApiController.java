package com.hotel.app.controller.api.v1;

import com.hotel.app.model.DataRecovery;
import com.hotel.app.service.DataRecoveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/data-recovery")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4321"})
public class DataRecoveryApiController {

    private final DataRecoveryService dataRecoveryService;

    @GetMapping
    public ResponseEntity<List<DataRecovery>> getAllRecoveryData() {
        return ResponseEntity.ok(dataRecoveryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataRecovery> getRecoveryDataById(@PathVariable Long id) {
        return dataRecoveryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DataRecovery> createRecoveryData(@RequestBody DataRecovery dataRecovery) {
        DataRecovery created = dataRecoveryService.save(dataRecovery);
        URI location = URI.create("/api/v1/data-recovery/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecoveryData(@PathVariable Long id) {
        dataRecoveryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}