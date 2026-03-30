package com.hotel.app.repository;

import com.hotel.app.model.DataRecovery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRecoveryRepository extends JpaRepository<DataRecovery, Long> {}
