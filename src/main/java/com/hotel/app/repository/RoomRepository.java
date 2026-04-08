package com.hotel.app.repository;
import java.util.Optional;

import com.hotel.app.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {


    boolean existsByCode(String code);


    Optional<Room> findByCode(String code);


    List<Room> findByHotelId(Long hotelId);


    List<Room> findByHotelIdAndState(Long hotelId, Room.RoomState state);


    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId AND r.state = 'AVAILABLE'")
    List<Room> findAvailableByHotel(@Param("hotelId") Long hotelId);
}