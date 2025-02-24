package com.hotel.app.repository;

import com.hotel.app.model.RecuperacionDatos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecuperacionDatosRepository extends JpaRepository<RecuperacionDatos, Long> {}
