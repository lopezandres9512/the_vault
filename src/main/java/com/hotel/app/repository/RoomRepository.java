package com.hotel.app.repository;

import com.hotel.app.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHotelId(Long hotelId);
    List<Room> findByHotelIdAndState(Long hotelId, Room.RoomState state);

    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId AND r.state = 'AVAILABLE'")
    List<Room> findAvailableRoomsByHotel(@Param("hotelId") Long hotelId);
}