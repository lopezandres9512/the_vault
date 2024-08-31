package com.hotel.app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idhabitacion", foreignKey = @ForeignKey(name = "fk_reserva_habitacion"))
    private Habitacion habitacion;

    @Column(name = "duracion")
    private Integer duracion;

    @Column(name = "fecha")
    private LocalDate fecha;
}
