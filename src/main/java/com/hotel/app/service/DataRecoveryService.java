package com.hotel.app.service;

import com.hotel.app.dto.request.CreateDataRecoveryDTO;
import com.hotel.app.dto.response.DataRecoveryResponseDTO;
import com.hotel.app.model.DataRecovery;
import com.hotel.app.model.Reservation;
import com.hotel.app.model.User;
import com.hotel.app.repository.DataRecoveryRepository;
import com.hotel.app.repository.ReservationRepository;
import com.hotel.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DataRecoveryService {

    private final DataRecoveryRepository dataRecoveryRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    public List<DataRecoveryResponseDTO> findAll() {
        return dataRecoveryRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Optional<DataRecoveryResponseDTO> findById(Long id) {
        return dataRecoveryRepository.findById(id)
                .map(this::toResponseDTO);
    }

    @Transactional
    public DataRecoveryResponseDTO create(CreateDataRecoveryDTO dto) {
        DataRecovery dataRecovery = new DataRecovery();
        dataRecovery.setOccupationDate(dto.occupationDate());

        reservationRepository.findById(dto.reservationId())
                .ifPresent(dataRecovery::setReservation);

        userRepository.findById(dto.userId())
                .ifPresent(dataRecovery::setUser);

        DataRecovery saved = dataRecoveryRepository.save(dataRecovery);
        return toResponseDTO(saved);
    }

    @Transactional
    public void delete(Long id) {
        dataRecoveryRepository.deleteById(id);
    }

    private DataRecoveryResponseDTO toResponseDTO(DataRecovery dataRecovery) {
        return new DataRecoveryResponseDTO(
                dataRecovery.getId(),
                dataRecovery.getOccupationDate(),
                dataRecovery.getReservation() != null ? dataRecovery.getReservation().getId() : null,
                dataRecovery.getReservation() != null ? dataRecovery.getReservation().getCode() : null,
                dataRecovery.getUser() != null ? dataRecovery.getUser().getId() : null,
                dataRecovery.getUser() != null ? dataRecovery.getUser().getName() : null
        );
    }
}