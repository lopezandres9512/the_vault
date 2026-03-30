package com.hotel.app.service;

import com.hotel.app.dto.request.CreateUserDTO;
import com.hotel.app.dto.request.LoginRequestDTO;
import com.hotel.app.dto.request.UpdateUserDTO;
import com.hotel.app.dto.response.AuthResponseDTO;
import com.hotel.app.dto.response.UserResponseDTO;
import com.hotel.app.model.User;
import com.hotel.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Optional<UserResponseDTO> findById(Long id) {
        return userRepository.findById(id)
                .map(this::toResponseDTO);
    }

    @Transactional
    public UserResponseDTO create(CreateUserDTO dto) {
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setUsername(dto.username());
        user.setPassword(dto.password()); // ⚠️ En producción, encriptar con BCrypt
        user.setUserType(dto.userType());

        User saved = userRepository.save(user);
        return toResponseDTO(saved);
    }

    @Transactional
    public Optional<UserResponseDTO> update(Long id, UpdateUserDTO dto) {
        return userRepository.findById(id)
                .map(existing -> {
                    if (dto.name() != null) {
                        existing.setName(dto.name());
                    }
                    if (dto.email() != null) {
                        existing.setEmail(dto.email());
                    }
                    if (dto.username() != null) {
                        existing.setUsername(dto.username());
                    }
                    if (dto.password() != null) {
                        existing.setPassword(dto.password()); // ⚠️ Encriptar en producción
                    }
                    if (dto.userType() != null) {
                        existing.setUserType(dto.userType());
                    }
                    return toResponseDTO(userRepository.save(existing));
                });
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public AuthResponseDTO login(LoginRequestDTO dto) {
        return userRepository.findByEmail(dto.email())
                .filter(user -> user.getPassword().equals(dto.password())) // ⚠️ Usar BCrypt en producción
                .map(user -> new AuthResponseDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getUserType().name(),
                        "Login successful",
                        true
                ))
                .orElse(new AuthResponseDTO(
                        null,
                        null,
                        null,
                        null,
                        "Invalid credentials",
                        false
                ));
    }

    private UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                user.getUserType(),
                user.getHotels() != null ? user.getHotels().size() : 0,
                0 // totalReservations (agregar si tienes relación directa)
        );
    }
}