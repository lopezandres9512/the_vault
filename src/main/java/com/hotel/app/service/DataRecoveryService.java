package com.hotel.app.service;

import com.hotel.app.model.DataRecovery;
import com.hotel.app.repository.DataRecoveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DataRecoveryService {

    private final DataRecoveryRepository dataRecoveryRepository;

    public List<DataRecovery> findAll() {
        return dataRecoveryRepository.findAll();
    }

    public Optional<DataRecovery> findById(Long id) {
        return dataRecoveryRepository.findById(id);
    }

    public DataRecovery save(DataRecovery dataRecovery) {
        return dataRecoveryRepository.save(dataRecovery);
    }

    public void delete(Long id) {
        dataRecoveryRepository.deleteById(id);
    }
}