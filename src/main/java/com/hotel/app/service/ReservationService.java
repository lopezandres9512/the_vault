package com.hotel.app.service;

import com.hotel.app.model.Reservation;
import com.hotel.app.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> update(Long id, Reservation reservation) {
        return reservationRepository.findById(id)
                .map(existing -> {
                    existing.setCode(reservation.getCode());
                    existing.setReservationDate(reservation.getReservationDate());
                    existing.setUser(reservation.getUser());
                    existing.setRoom(reservation.getRoom());
                    return reservationRepository.save(existing);
                });
    }

    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }
}