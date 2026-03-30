package com.hotel.app.repository;

import com.hotel.app.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId);
    List<Reservation> findByRoomId(Long roomId);

    @Query("SELECT r FROM Reservation r WHERE r.user.id = :userId AND r.reservationDate >= :date")
    List<Reservation> findFutureReservationsByUser(@Param("userId") Long userId, @Param("date") LocalDate date);
}