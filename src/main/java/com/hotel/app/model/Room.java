package com.hotel.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String size;
    private int personQuantity;
    @Enumerated(EnumType.STRING)
    private RoomState state;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    public enum RoomState {
        DISPONIBLE, OCUPADO
    }
}
