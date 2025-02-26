package org.example;

public class Vendor implements Runnable {
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
        for (int i = 0; i < totalTickets; i++) {
            synchronized (ticketPool) {
                if (ticketPool.addTicket()) {
                    Logger.info(Thread.currentThread().getName() + " added a ticket.");
                } else {
                    Logger.warn("Ticket pool is full.");
                }
            }
            try {
                Thread.sleep(1000 / ticketReleaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Logger.error(Thread.currentThread().getName() + " was interrupted while releasing tickets.");
            }
        }
    }
}

