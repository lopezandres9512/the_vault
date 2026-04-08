package com.hotel.app.repository;
import java.util.Optional;

import com.hotel.app.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    boolean existsByCode(String code);


    Optional<Reservation> findByCode(String code);


    List<Reservation> findByUserId(Long userId);


    List<Reservation> findByRoomId(Long roomId);
}