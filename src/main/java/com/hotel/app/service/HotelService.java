package com.hotel.app.service;

import com.hotel.app.model.Hotel;
import com.hotel.app.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> findById(Long id) {
        return hotelRepository.findById(id);
    }

    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Optional<Hotel> update(Long id, Hotel hotel) {
        return hotelRepository.findById(id)
                .map(existing -> {
                    existing.setName(hotel.getName());
                    existing.setCode(hotel.getCode());
                    existing.setRoomQuantity(hotel.getRoomQuantity());
                    existing.setAdministrator(hotel.getAdministrator());
                    return hotelRepository.save(existing);
                });
    }

    public void delete(Long id) {
        hotelRepository.deleteById(id);
    }
}