package com.example.TicketBooking.Backend.TicketBooking.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Customer implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Customer.class);

    private final TicketPool ticketPool;
    private final int retrievalRate;

    public Customer(TicketPool ticketPool, int retrievalRate) {
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
    }

    @Override
    public void run() {
        logger.info("{} started retrieving tickets.", Thread.currentThread().getName());

        while (!ticketPool.isSimulationComplete()) {
            synchronized (ticketPool) {
                if (ticketPool.removeTicket()) {
                    logger.info("{} retrieved a ticket.", Thread.currentThread().getName());
                } else {
                    logger.warn("{}: No tickets available.", Thread.currentThread().getName());
                }
            }

            try {
                Thread.sleep(1000 / retrievalRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Customer thread interrupted.", e);
                return; // Exit the loop on interruption
            }
        }

        logger.info("{} has finished retrieving tickets.", Thread.currentThread().getName());
    }
}
