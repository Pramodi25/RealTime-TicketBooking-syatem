package com.example.TicketBooking.Backend.TicketBooking.controller;

import com.example.TicketBooking.Backend.TicketBooking.Model.Customer;
import com.example.TicketBooking.Backend.TicketBooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public Customer createConfiguration(@RequestBody Customer config) {
        return service.save(config);
    }

    @GetMapping("/{id}")
    public Optional<Customer> getConfigurationById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Customer> getAllConfigurations() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteConfigurationById(@PathVariable String id) {
        service.deleteById(id);
    }
}
