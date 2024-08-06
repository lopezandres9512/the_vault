package com.hotel.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.Column;
import java.time.LocalDate;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

