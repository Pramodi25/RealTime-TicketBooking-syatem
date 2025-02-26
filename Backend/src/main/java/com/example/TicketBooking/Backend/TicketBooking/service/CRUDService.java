package com.example.TicketBooking.Backend.TicketBooking.service;

import java.util.List;
import java.util.Optional;

public interface CRUDService<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
}
