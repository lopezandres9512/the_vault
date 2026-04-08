package com.hotel.app.service;

import com.hotel.app.dto.request.CreateHotelDTO;
import com.hotel.app.dto.request.UpdateHotelDTO;
import com.hotel.app.dto.response.HotelResponseDTO;
import com.hotel.app.model.Hotel;
import com.hotel.app.model.User;
import com.hotel.app.repository.HotelRepository;
import com.hotel.app.repository.UserRepository;
import com.hotel.app.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;

    public List<HotelResponseDTO> findAll() {
        return hotelRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<HotelResponseDTO> findById(Long id) {
        return hotelRepository.findById(id)
                .map(this::toResponseDTO);
    }

    @Transactional
    public HotelResponseDTO create(CreateHotelDTO dto) {
        if (hotelRepository.existsByCode(dto.code())) {
            throw new RuntimeException("Hotel code already exists: " + dto.code());
        }

        Hotel hotel = new Hotel();
        hotel.setName(dto.name());
        hotel.setCode(dto.code());
        hotel.setRoomQuantity(dto.roomQuantity());

        if (dto.administratorId() != null) {
            User admin = userRepository.findById(dto.administratorId())
                    .orElseThrow(() -> new ResourceNotFoundException("User", dto.administratorId()));
            hotel.setAdministrator(admin);
        }

        Hotel saved = hotelRepository.save(hotel);
        return toResponseDTO(saved);
    }

    @Transactional
    public Optional<HotelResponseDTO> update(Long id, UpdateHotelDTO dto) {
        return hotelRepository.findById(id)
                .map(existing -> {
                    if (dto.name() != null) existing.setName(dto.name());
                    if (dto.code() != null) existing.setCode(dto.code());
                    if (dto.roomQuantity() != null) existing.setRoomQuantity(dto.roomQuantity());
                    if (dto.administratorId() != null) {
                        User admin = userRepository.findById(dto.administratorId())
                                .orElseThrow(() -> new ResourceNotFoundException("User", dto.administratorId()));
                        existing.setAdministrator(admin);
                    }
                    return toResponseDTO(hotelRepository.save(existing));
                });
    }

    @Transactional
    public void delete(Long id) {
        hotelRepository.deleteById(id);
    }

    private HotelResponseDTO toResponseDTO(Hotel hotel) {
        return new HotelResponseDTO(
                hotel.getId(),
                hotel.getName(),
                hotel.getCode(),
                hotel.getRoomQuantity(),
                hotel.getAdministrator() != null ? hotel.getAdministrator().getId() : null,
                hotel.getAdministrator() != null ? hotel.getAdministrator().getName() : null,
                hotel.getRooms() != null ? hotel.getRooms().size() : 0,
                null
        );
    }
}