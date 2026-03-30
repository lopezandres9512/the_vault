package com.hotel.app.repository;

import com.hotel.app.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Optional<Hotel> findByCode(String code);
    List<Hotel> findByAdministratorId(Long administratorId);
    boolean existsByCode(String code);
}