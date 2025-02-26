package com.example.TicketBooking.Backend.TicketBooking.controller;

import com.example.TicketBooking.Backend.TicketBooking.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    private final TicketService ticketingService;

    @Autowired
    public TicketController(TicketService ticketingService) {
        this.ticketingService = ticketingService;
    }

    /**
     * Endpoint to start the ticketing system
     */
    @PostMapping("/system/start")
    public ResponseEntity<String> startSystem() {
        try {
            logger.info("Received request to start the system.");
            ticketingService.startSystem();
            logger.info("System started successfully.");
            return ResponseEntity.ok("System started successfully.");
        } catch (IllegalStateException e) {
            logger.warn("System is already running: {}", e.getMessage());
            return ResponseEntity.badRequest().body("System is already running: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to start the system: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Failed to start the system: " + e.getMessage());
        }
    }

    /**
     * Endpoint to stop the ticketing system
     */
    @PostMapping("/system/stop")
    public ResponseEntity<String> stopSystem() {
        try {
            logger.info("Received request to stop the system.");
            ticketingService.stopSystem();
            logger.info("System stopped successfully.");
            return ResponseEntity.ok("System stopped successfully.");
        } catch (Exception e) {
            logger.error("Failed to stop the system: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Failed to stop the system: " + e.getMessage());
        }
    }

    /**
     * Endpoint to reset the ticketing system
     */
    @PostMapping("/system/reset")
    public ResponseEntity<String> resetSystem() {
        try {
            logger.info("Received request to reset the system.");
            ticketingService.resetSystem();
            logger.info("System reset successfully.");
            return ResponseEntity.ok("System reset successfully.");
        } catch (Exception e) {
            logger.error("Failed to reset the system: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Failed to reset the system: " + e.getMessage());
        }
    }

    /**
     * Endpoint to get the system's status
     */
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getSystemStatus() {
        Map<String, Object> status = new HashMap<>();
        try {
            logger.info("Received request to fetch system status.");
            status.put("currentTickets", ticketingService.getCurrentTicketCount());
            status.put("maxCapacity", ticketingService.getMaxTicketCapacity());
            status.put("isRunning", ticketingService.isSystemRunning());
            logger.info("System status fetched successfully: {}", status);
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            logger.error("Failed to fetch system status: {}", e.getMessage(), e);
            status.put("error", "Failed to fetch status: " + e.getMessage());
            return ResponseEntity.internalServerError().body(status);
        }
    }
}
