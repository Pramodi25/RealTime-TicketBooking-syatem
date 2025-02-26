package com.example.TicketBooking.Backend.TicketBooking.repository;

import com.example.TicketBooking.Backend.TicketBooking.Model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

}

