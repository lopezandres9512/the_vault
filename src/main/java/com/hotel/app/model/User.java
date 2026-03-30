package com.hotel.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String nameUser;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(mappedBy = "administrator")
    private List<Hotel> hotels;
}

enum UserType {
    GUEST, ADMINISTRATOR
}