package com.example.TicketBooking.Backend.TicketBooking.controller;

import com.example.TicketBooking.Backend.TicketBooking.Model.Vendor;
import com.example.TicketBooking.Backend.TicketBooking.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    private final VendorService service;

    @Autowired
    public VendorController(VendorService service) {
        this.service = service;
    }

    @PostMapping
    public Vendor createVendor(@RequestBody Vendor config) {
        return service.save(config);
    }

    @GetMapping("/{id}")
    public Optional<Vendor> getVendorById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Vendor> getAllVendors() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteVendorById(@PathVariable String id) {
        service.deleteById(id);
    }
}
