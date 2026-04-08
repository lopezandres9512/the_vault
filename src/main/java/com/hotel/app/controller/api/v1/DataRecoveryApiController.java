package com.hotel.app.controller.api.v1;

// ✅ SOLO DTOs - NO importar entidades
import com.hotel.app.dto.request.CreateDataRecoveryDTO;
import com.hotel.app.dto.response.DataRecoveryResponseDTO;
import com.hotel.app.service.DataRecoveryService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<DataRecoveryResponseDTO>> getAllRecoveryData() {
        List<DataRecoveryResponseDTO> data = dataRecoveryService.findAll();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataRecoveryResponseDTO> getRecoveryDataById(@PathVariable Long id) {
        return dataRecoveryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DataRecoveryResponseDTO> createRecoveryData(
            @Valid @RequestBody CreateDataRecoveryDTO dto) {
        DataRecoveryResponseDTO created = dataRecoveryService.create(dto);
        URI location = URI.create("/api/v1/data-recovery/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecoveryData(@PathVariable Long id) {
        dataRecoveryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}