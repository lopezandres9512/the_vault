package com.hotel.app.service;

import com.hotel.app.model.Room;
import com.hotel.app.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    public List<Room> findByHotelId(Long hotelId) {
        return roomRepository.findAll().stream()
                .filter(room -> room.getHotel() != null && room.getHotel().getId().equals(hotelId))
                .toList();
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public Optional<Room> update(Long id, Room room) {
        return roomRepository.findById(id)
                .map(existing -> {
                    existing.setCode(room.getCode());
                    existing.setSize(room.getSize());
                    existing.setPersonQuantity(room.getPersonQuantity());
                    existing.setState(room.getState());
                    existing.setHotel(room.getHotel());
                    return roomRepository.save(existing);
                });
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}
