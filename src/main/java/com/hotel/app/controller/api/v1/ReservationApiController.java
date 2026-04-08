package com.hotel.app.controller.api.v1;

// ✅ SOLO DTOs - NO importar entidades
import com.hotel.app.dto.request.CreateReservationDTO;
import com.hotel.app.dto.request.UpdateReservationDTO;
import com.hotel.app.dto.response.ReservationResponseDTO;
import com.hotel.app.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4321"})
public class ReservationApiController {

    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> getAllReservations() {
        List<ReservationResponseDTO> data = reservationService.findAll();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id) {
        return reservationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(
            @Valid @RequestBody CreateReservationDTO dto) {  // ✅ DTO de entrada
        ReservationResponseDTO created = reservationService.create(dto);  // ✅ DTO de salida
        URI location = URI.create("/api/v1/reservations/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> updateReservation(
            @PathVariable Long id,
            @Valid @RequestBody UpdateReservationDTO dto) {  // ✅ DTO de entrada
        return reservationService.update(id, dto)  // ✅ Retorna Optional<DTO>
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}