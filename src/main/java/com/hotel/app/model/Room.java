package com.hotel.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String size;
    private Integer personQuantity;

    @Enumerated(EnumType.STRING)
    private RoomState state;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public enum RoomState {
        AVAILABLE,
        OCCUPIED
    }
}