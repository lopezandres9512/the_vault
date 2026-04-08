package com.hotel.app.service;

import com.hotel.app.dto.request.CreateRoomDTO;
import com.hotel.app.dto.request.UpdateRoomDTO;
import com.hotel.app.dto.response.RoomResponseDTO;
import com.hotel.app.model.Hotel;
import com.hotel.app.model.Room;
import com.hotel.app.repository.HotelRepository;
import com.hotel.app.repository.RoomRepository;
import com.hotel.app.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public List<RoomResponseDTO> findAll() {
        return roomRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<RoomResponseDTO> findById(Long id) {
        return roomRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public List<RoomResponseDTO> findByHotelId(Long hotelId) {
        return roomRepository.findByHotelId(hotelId).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public RoomResponseDTO create(CreateRoomDTO dto) {
        if (roomRepository.existsByCode(dto.code())) {
            throw new RuntimeException("Room code already exists: " + dto.code());
        }

        Room room = new Room();
        room.setCode(dto.code());
        room.setSize(dto.size());
        room.setPersonQuantity(dto.personQuantity());
        room.setState(dto.state());

        Hotel hotel = hotelRepository.findById(dto.hotelId())
                .orElseThrow(() -> new ResourceNotFoundException("Hotel", dto.hotelId()));
        room.setHotel(hotel);

        Room saved = roomRepository.save(room);
        return toResponseDTO(saved);
    }

    @Transactional
    public Optional<RoomResponseDTO> update(Long id, UpdateRoomDTO dto) {
        return roomRepository.findById(id)
                .map(existing -> {
                    if (dto.code() != null) existing.setCode(dto.code());
                    if (dto.size() != null) existing.setSize(dto.size());
                    if (dto.personQuantity() != null) existing.setPersonQuantity(dto.personQuantity());
                    if (dto.state() != null) existing.setState(dto.state());
                    if (dto.hotelId() != null) {
                        Hotel hotel = hotelRepository.findById(dto.hotelId())
                                .orElseThrow(() -> new ResourceNotFoundException("Hotel", dto.hotelId()));
                        existing.setHotel(hotel);
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