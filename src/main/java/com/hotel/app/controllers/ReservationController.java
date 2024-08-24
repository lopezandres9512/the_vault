package com.hotel.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservation-controller")
public class ReservationController {
    @GetMapping
    public String get() {
        return "works!";
    }
}
