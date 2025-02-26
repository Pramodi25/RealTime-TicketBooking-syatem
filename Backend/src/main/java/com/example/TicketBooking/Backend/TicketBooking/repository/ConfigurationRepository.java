package com.example.TicketBooking.Backend.TicketBooking.repository;

import com.example.TicketBooking.Backend.TicketBooking.Model.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends MongoRepository<Configuration, String> {
}

