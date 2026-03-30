package com.hotel.app.repository;

import com.hotel.app.model.DataRecovery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DataRecoveryRepository extends JpaRepository<DataRecovery, Long> {
    List<DataRecovery> findByUserId(Long userId);
    List<DataRecovery> findByReservationId(Long reservationId);
}