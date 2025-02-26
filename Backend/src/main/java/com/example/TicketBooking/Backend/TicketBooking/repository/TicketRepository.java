package com.example.TicketBooking.Backend.TicketBooking.repository;

import com.example.TicketBooking.Backend.TicketBooking.Model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {
}

