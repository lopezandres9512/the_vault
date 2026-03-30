package com.hotel.app.service;

import com.hotel.app.dto.request.CreateHotelDTO;
import com.hotel.app.dto.request.UpdateHotelDTO;
import com.hotel.app.dto.response.HotelResponseDTO;
import com.hotel.app.model.Hotel;
import com.hotel.app.model.User;
import com.hotel.app.repository.HotelRepository;
import com.hotel.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;

    public List<HotelResponseDTO> findAll() {
        return hotelRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Optional<HotelResponseDTO> findById(Long id) {
        return hotelRepository.findById(id)
                .map(this::toResponseDTO);
    }

    @Transactional
    public HotelResponseDTO create(CreateHotelDTO dto) {
        Hotel hotel = new Hotel();
        hotel.setName(dto.name());
        hotel.setCode(dto.code());
        hotel.setRoomQuantity(dto.roomQuantity());

        if (dto.administratorId() != null) {
            userRepository.findById(dto.administratorId())
                    .ifPresent(hotel::setAdministrator);
        }

        Hotel saved = hotelRepository.save(hotel);
        return toResponseDTO(saved);
    }

    @Transactional
    public Optional<HotelResponseDTO> update(Long id, UpdateHotelDTO dto) {
        return hotelRepository.findById(id)
                .map(existing -> {
                    existing.setName(dto.name());
                    existing.setCode(dto.code());
                    existing.setRoomQuantity(dto.roomQuantity());

                    if (dto.administratorId() != null) {
                        userRepository.findById(dto.administratorId())
                                .ifPresent(existing::setAdministrator);
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
                null // createdAt (agregar si tienes @CreatedDate)
        );
    }
}