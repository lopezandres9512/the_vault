package com.hotel.app.controller;

import com.hotel.app.model.*;
import com.hotel.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/reservation-controller")
public class ReservationController {
    @GetMapping
    public String get() {
        return "works!";
    }
}
