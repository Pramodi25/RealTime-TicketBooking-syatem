package com.example.TicketBooking.Backend.TicketBooking.repository;

import com.example.TicketBooking.Backend.TicketBooking.Model.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends MongoRepository<Vendor, String> {
}

