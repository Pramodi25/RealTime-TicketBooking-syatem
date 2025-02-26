package com.example.TicketBooking.Backend.TicketBooking.controller;

import com.example.TicketBooking.Backend.TicketBooking.Model.Configuration;
import com.example.TicketBooking.Backend.TicketBooking.service.ConfigurationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/configuration")
public class ConfigurationController {
    private final ConfigurationService service;

    public ConfigurationController(ConfigurationService service) {
        this.service = service;
    }

    @GetMapping
    public Configuration getConfiguration() {
        return service.getConfigurationById("default");
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> updateConfiguration(@RequestBody Configuration config) {
        config.setId("default");
        service.saveConfiguration(config);
        // Return a valid JSON response
        Map<String, String> response = new HashMap<>();
        response.put("message", "Configuration updated successfully.");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public void deleteConfigurationById() {
        service.deleteConfigurationById("default");
    }
}

