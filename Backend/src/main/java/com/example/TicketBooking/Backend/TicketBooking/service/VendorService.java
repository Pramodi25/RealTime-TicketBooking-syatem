package com.example.TicketBooking.Backend.TicketBooking.service;

import com.example.TicketBooking.Backend.TicketBooking.Model.Vendor;
import com.example.TicketBooking.Backend.TicketBooking.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService implements CRUDService<Vendor, String> {

    private final VendorRepository repository;

    @Autowired
    public VendorService(VendorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vendor save(Vendor vendor) {
        return repository.save(vendor);
    }

    @Override
    public Optional<Vendor> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Vendor> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
