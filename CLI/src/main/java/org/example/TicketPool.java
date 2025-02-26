package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    // Queue to store tickets
    private final Queue<Integer> tickets = new LinkedList<>();
    private final int capacity;
    private boolean simulationComplete = false;

    public TicketPool(int capacity) {
        this.capacity = capacity;
    }

    public synchronized boolean addTicket() {
        if (tickets.size() < capacity) {
            tickets.add(1); // Add a ticket
            notifyAll();
            return true;
        }
        return false;
    }

    public synchronized boolean removeTicket() {
        while (tickets.isEmpty() && !simulationComplete) {
            try {
                wait(); // Wait for tickets to be added or simulation to complete
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        if (!tickets.isEmpty()) {
            tickets.poll(); // Remove a ticket
            notifyAll();
            return true;
        }
        return false; // No tickets available and simulation is complete
    }

    public synchronized void setSimulationComplete() {
        simulationComplete = true;
        notifyAll(); // Wake up any waiting threads
    }

    // Check if simulation is complete and no tickets are left
    public synchronized boolean isSimulationComplete() {
        return simulationComplete && tickets.isEmpty();
    }
}
