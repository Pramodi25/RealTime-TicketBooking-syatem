package com.example.TicketBooking.Backend.TicketBooking.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Vendor implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Vendor.class);

    private final TicketPool ticketPool;
    private final int totalTickets;
    private final int ticketReleaseRate;

    public Vendor(TicketPool ticketPool, int totalTickets, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        logger.info("{} started adding tickets.", Thread.currentThread().getName());
        int ticketsAdded = 0;

        while (ticketsAdded < totalTickets) {
            synchronized (ticketPool) {
                if (ticketPool.addTicket()) {
                    ticketsAdded++;
                    logger.info("{} added a ticket. Total tickets added: {}", Thread.currentThread().getName(), ticketsAdded);
                } else {
                    logger.warn("Ticket pool is full. {} is waiting to add tickets.", Thread.currentThread().getName());
                }
            }

            try {
                Thread.sleep(1000 / ticketReleaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Vendor thread interrupted.", e);
                return; // Exit the loop on interruption
            }
        }

        logger.info("{} has finished adding all tickets. Total tickets added: {}", Thread.currentThread().getName(), ticketsAdded);
    }
}
