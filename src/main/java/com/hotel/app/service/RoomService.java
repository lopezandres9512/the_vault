package com.hotel.app.service;

import com.hotel.app.dto.request.CreateRoomDTO;
import com.hotel.app.dto.request.UpdateRoomDTO;
import com.hotel.app.dto.response.RoomResponseDTO;
import com.hotel.app.model.Hotel;
import com.hotel.app.model.Room;
import com.hotel.app.repository.HotelRepository;
import com.hotel.app.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public List<RoomResponseDTO> findAll() {
        return roomRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Optional<RoomResponseDTO> findById(Long id) {
        return roomRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public List<RoomResponseDTO> findByHotelId(Long hotelId) {
        return roomRepository.findAll().stream()
                .filter(room -> room.getHotel() != null && room.getHotel().getId().equals(hotelId))
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional
    public RoomResponseDTO create(CreateRoomDTO dto) {
        Room room = new Room();
        room.setCode(dto.code());
        room.setSize(dto.size());
        room.setPersonQuantity(dto.personQuantity());
        room.setState(dto.state());

        hotelRepository.findById(dto.hotelId())
                .ifPresent(room::setHotel);

        Room saved = roomRepository.save(room);
        return toResponseDTO(saved);
    }

    @Transactional
    public Optional<RoomResponseDTO> update(Long id, UpdateRoomDTO dto) {
        return roomRepository.findById(id)
                .map(existing -> {
                    existing.setCode(dto.code());
                    existing.setSize(dto.size());
                    existing.setPersonQuantity(dto.personQuantity());

                    if (dto.state() != null) {
                        existing.setState(dto.state());
                    }

                    if (dto.hotelId() != null) {
                        hotelRepository.findById(dto.hotelId())
                                .ifPresent(existing::setHotel);
                    }

                    return toResponseDTO(roomRepository.save(existing));
                });
    }

    @Transactional
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

    private RoomResponseDTO toResponseDTO(Room room) {
        return new RoomResponseDTO(
                room.getId(),
                room.getCode(),
                room.getSize(),
                room.getPersonQuantity(),
                room.getState(),
                room.getHotel() != null ? room.getHotel().getId() : null,
                room.getHotel() != null ? room.getHotel().getName() : null
        );
    }
}