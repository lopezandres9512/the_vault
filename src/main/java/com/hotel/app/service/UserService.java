package com.hotel.app.service;

import com.hotel.app.dto.request.CreateUserDTO;
import com.hotel.app.dto.request.UpdateUserDTO;
import com.hotel.app.dto.response.UserResponseDTO;
import com.hotel.app.model.User;
import com.hotel.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // ✅ CORREGIDO: Convierte List<User> → List<UserResponseDTO>
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // ✅ CORREGIDO: Convierte Optional<User> → Optional<UserResponseDTO>
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
        user.setPassword(dto.password()); // ⚠️ Encriptar en producción
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
                        existing.setPassword(dto.password());
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

    // ===========================================
    // MÉTODO DE MAPEO: Entity → DTO
    // ===========================================
    private UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                user.getUserType(),
                user.getHotels() != null ? user.getHotels().size() : 0,
                0 // totalReservations
        );
    }
}