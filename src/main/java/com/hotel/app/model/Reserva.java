package com.hotel.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idhabitacion", foreignKey = @ForeignKey(name = "fk_reserva_habitacion"))
    private Habitacion habitacion;

    @Column(name = "numero_de_habitaciones")
    private Integer numeroDeHabitaciones;

    @Column(name = "duracion")
    private Integer duracion;

    @Column(name = "fecha")
    private LocalDate fecha;
}
