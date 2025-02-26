package com.example.TicketBooking.Backend.TicketBooking.Model;

import lombok.Data;

@Data
public class Ticket {
    private final int id;
    private final double price;
    private String status; // e.g., "available", "sold", "failed"

    public Ticket(int id, double price, String status) {
        this.id = id;
        this.price = price;
        this.status = status;
    }
}

