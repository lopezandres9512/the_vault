package com.hotel.app.service;

import com.hotel.app.dto.request.CreateReservationDTO;
import com.hotel.app.dto.request.UpdateReservationDTO;
import com.hotel.app.dto.response.ReservationResponseDTO;
import com.hotel.app.model.Reservation;
import com.hotel.app.model.Room;
import com.hotel.app.model.User;
import com.hotel.app.repository.ReservationRepository;
import com.hotel.app.repository.RoomRepository;
import com.hotel.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public List<ReservationResponseDTO> findAll() {
        return reservationRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Optional<ReservationResponseDTO> findById(Long id) {
        return reservationRepository.findById(id)
                .map(this::toResponseDTO);
    }

    @Transactional
    public ReservationResponseDTO create(CreateReservationDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setCode(dto.code());
        reservation.setReservationDate(dto.reservationDate());

        userRepository.findById(dto.userId())
                .ifPresent(reservation::setUser);

        roomRepository.findById(dto.roomId())
                .ifPresent(reservation::setRoom);

        Reservation saved = reservationRepository.save(reservation);
        return toResponseDTO(saved);
    }

    @Transactional
    public Optional<ReservationResponseDTO> update(Long id, UpdateReservationDTO dto) {
        return reservationRepository.findById(id)
                .map(existing -> {
                    if (dto.code() != null) {
                        existing.setCode(dto.code());
                    }
                    if (dto.reservationDate() != null) {
                        existing.setReservationDate(dto.reservationDate());
                    }
                    if (dto.userId() != null) {
                        userRepository.findById(dto.userId())
                                .ifPresent(existing::setUser);
                    }
                    if (dto.roomId() != null) {
                        roomRepository.findById(dto.roomId())
                                .ifPresent(existing::setRoom);
                    }
                    return toResponseDTO(reservationRepository.save(existing));
                });
    }

    @Transactional
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    private ReservationResponseDTO toResponseDTO(Reservation reservation) {
        return new ReservationResponseDTO(
                reservation.getId(),
                reservation.getCode(),
                reservation.getReservationDate(),
                reservation.getUser() != null ? reservation.getUser().getId() : null,
                reservation.getUser() != null ? reservation.getUser().getName() : null,
                reservation.getRoom() != null ? reservation.getRoom().getId() : null,
                reservation.getRoom() != null ? reservation.getRoom().getCode() : null,
                reservation.getRoom() != null && reservation.getRoom().getHotel() != null
                        ? reservation.getRoom().getHotel().getName()
                        : null
        );
    }
}