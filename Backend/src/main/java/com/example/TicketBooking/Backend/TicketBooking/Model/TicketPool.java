package com.example.TicketBooking.Backend.TicketBooking.Model;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketPool {
    private final Queue<Integer> tickets = new ConcurrentLinkedQueue<>();
    private final int capacity;

    public TicketPool(int capacity) {
        this.capacity = capacity;
    }

    public synchronized boolean addTicket() {
        if (tickets.size() < capacity) {
            tickets.add(1); // Add a ticket
            return true;
        }
        return false;
    }

    public synchronized boolean removeTicket() {
        return tickets.poll() != null;
    }

    public int getCurrentTicketCount() {
        return tickets.size();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public synchronized boolean isSimulationComplete() {
        return tickets.isEmpty();
    }
}
