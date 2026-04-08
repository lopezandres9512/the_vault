package com.hotel.app.service;

import com.hotel.app.dto.request.CreateDataRecoveryDTO;
import com.hotel.app.dto.response.DataRecoveryResponseDTO;
import com.hotel.app.model.DataRecovery;
import com.hotel.app.model.Reservation;
import com.hotel.app.model.User;
import com.hotel.app.repository.DataRecoveryRepository;
import com.hotel.app.repository.ReservationRepository;
import com.hotel.app.repository.UserRepository;
import com.hotel.app.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataRecoveryService {

    private final DataRecoveryRepository dataRecoveryRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;


    public List<DataRecoveryResponseDTO> findAll() {
        return dataRecoveryRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<DataRecoveryResponseDTO> findById(Long id) {
        return dataRecoveryRepository.findById(id)
                .map(this::toResponseDTO);
    }

    @Transactional
    public DataRecoveryResponseDTO create(CreateDataRecoveryDTO dto) {
        Reservation reservation = reservationRepository.findById(dto.reservationId())
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", dto.reservationId()));
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User", dto.userId()));

        DataRecovery dataRecovery = new DataRecovery();
        dataRecovery.setOccupationDate(dto.occupationDate());
        dataRecovery.setReservation(reservation);
        dataRecovery.setUser(user);

        DataRecovery saved = dataRecoveryRepository.save(dataRecovery);
        return toResponseDTO(saved);
    }

    @Transactional
    public void delete(Long id) {
        dataRecoveryRepository.deleteById(id);
    }


    private DataRecoveryResponseDTO toResponseDTO(DataRecovery entity) {
        return new DataRecoveryResponseDTO(
                entity.getId(),
                entity.getOccupationDate(),
                entity.getReservation() != null ? entity.getReservation().getId() : null,
                entity.getReservation() != null ? entity.getReservation().getCode() : null,
                entity.getUser() != null ? entity.getUser().getId() : null,
                entity.getUser() != null ? entity.getUser().getName() : null
        );
    }
}