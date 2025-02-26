package com.example.TicketBooking.Backend.TicketBooking.service;

import com.example.TicketBooking.Backend.TicketBooking.Model.Configuration;
import com.example.TicketBooking.Backend.TicketBooking.Model.Customer;
import com.example.TicketBooking.Backend.TicketBooking.Model.TicketPool;
import com.example.TicketBooking.Backend.TicketBooking.Model.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TicketService {
    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

    private TicketPool ticketPool;
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private boolean isRunning = false;

    private final ConfigurationService configurationService;

    private ExecutorService vendorExecutor;
    private ExecutorService customerExecutor;

    public TicketService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
        logger.info("Initializing TicketService with default configuration.");

        // Default configuration
        this.maxTicketCapacity = 100; // Default max capacity
        this.ticketPool = new TicketPool(maxTicketCapacity);
        this.vendorExecutor = Executors.newFixedThreadPool(5); // Default for vendor threads
        this.customerExecutor = Executors.newFixedThreadPool(5); // Default for customer threads
        logger.info("TicketService initialized with maxTicketCapacity: {}", maxTicketCapacity);
    }

    public void updateConfiguration() {
        try {
            Configuration config = configurationService.getConfigurationById("default");
            if (config == null) {
                throw new IllegalStateException("Configuration with ID 'default' not found.");
            }
            this.totalTickets = config.getTotalTickets();
            this.ticketReleaseRate = config.getTicketReleaseRate();
            this.customerRetrievalRate = config.getCustomerRetrievalRate();
            this.maxTicketCapacity = config.getMaxTicketCapacity();

            logger.info("Loaded configuration from DB: totalTickets={}, ticketReleaseRate={}, customerRetrievalRate={}, maxTicketCapacity={}",
                    totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);

            // Reinitialize TicketPool with updated capacity
            synchronized (this) {
                this.ticketPool = new TicketPool(maxTicketCapacity);
            }
            logger.info("Configuration updated successfully.");
        } catch (Exception e) {
            logger.error("Failed to update configuration: {}", e.getMessage(), e);
            throw new RuntimeException("Error updating configuration", e);
        }
    }

    public void startSystem() {
        if (isRunning) {
            logger.error("Attempted to start system, but it is already running.");
            throw new IllegalStateException("System is already running");
        }
        isRunning = true;
        logger.info("Starting the system.");
        this.updateConfiguration();

        // Start vendor threads
        for (int i = 0; i < 4; i++) {
            logger.info("Starting vendor thread {}", i + 1);
            vendorExecutor.execute(new Vendor(ticketPool, totalTickets, ticketReleaseRate));
        }

        // Start customer threads
        for (int i = 0; i < 300; i++) {
            logger.info("Starting customer thread {}", i + 1);
            customerExecutor.execute(new Customer(ticketPool, customerRetrievalRate));
        }

        logger.info("System started successfully.");
    }

    public void stopSystem() {
        if (!isRunning) {
            logger.error("Attempted to stop system, but it is not running.");
            throw new IllegalStateException("System is not running");
        }
        isRunning = false;
        logger.info("Stopping the system.");

        // Shutdown vendor and customer threads gracefully
        vendorExecutor.shutdownNow();
        customerExecutor.shutdownNow();
        logger.info("All threads have been stopped.");

        // Reinitialize the executors
        this.vendorExecutor = Executors.newFixedThreadPool(5);
        this.customerExecutor = Executors.newFixedThreadPool(5);
        logger.info("System stopped and executors reinitialized.");
    }

    public void resetSystem() {
        logger.info("Resetting the system.");
        stopSystem();
        // Clear TicketPool and reset configuration
        synchronized (this) {
            this.ticketPool = new TicketPool(maxTicketCapacity);
        }
        this.isRunning = false;
        logger.info("System has been reset successfully.");
    }

    public int getCurrentTicketCount() {
        int currentCount;
        synchronized (this) {
            currentCount = ticketPool.getCurrentTicketCount(); // Assuming TicketPool provides this method
        }
        logger.info("Current ticket count: {}", currentCount);
        return currentCount;
    }

    public int getMaxTicketCapacity() {
        logger.info("Fetching max ticket capacity: {}", maxTicketCapacity);
        return this.maxTicketCapacity;
    }

    public boolean isSystemRunning() {
        logger.info("System running status: {}", isRunning);
        return this.isRunning;
    }

    public void startVendor() {
        logger.info("Starting a new vendor thread.");
        vendorExecutor.execute(new Vendor(ticketPool, totalTickets, ticketReleaseRate));
    }

    public void startCustomer() {
        logger.info("Starting a new customer thread.");
        customerExecutor.execute(new Customer(ticketPool, customerRetrievalRate));
    }
}
