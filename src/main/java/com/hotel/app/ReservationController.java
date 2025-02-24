package com.hotel.app;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservaciones")
public class ReservationController {
    @GetMapping
    public String get() {
        return "works!";
    }
}
