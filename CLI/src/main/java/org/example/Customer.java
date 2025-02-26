package org.example;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int retrievalRate;

    public Customer(TicketPool ticketPool, int retrievalRate) {
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
    }

    @Override
    public void run() {
        while (!ticketPool.isSimulationComplete()) { // Check if simulation is complete
            if (ticketPool.removeTicket()) {
                Logger.info(Thread.currentThread().getName() + " purchased a ticket.");
            } else {
                Logger.warn(Thread.currentThread().getName() + ": No tickets available.");
            }

            try {
                Thread.sleep(1000 / retrievalRate); // Simulate retrieval rate
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Logger.error(Thread.currentThread().getName() + " was interrupted while retrieving tickets.");
            }
        }
        Logger.info(Thread.currentThread().getName() + " stopped as simulation is complete.");
    }
}
