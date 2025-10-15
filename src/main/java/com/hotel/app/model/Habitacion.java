package com.hotel.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@Entity
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String tamanio;
    private int cantidadPersonas;
    @Enumerated(EnumType.STRING)
    private EstadoHabitacion estado;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    public enum EstadoHabitacion {
        DISPONIBLE, OCUPADO
    }
}
