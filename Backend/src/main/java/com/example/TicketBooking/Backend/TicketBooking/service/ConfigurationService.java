package com.example.TicketBooking.Backend.TicketBooking.service;

import com.example.TicketBooking.Backend.TicketBooking.Model.Configuration;
import com.example.TicketBooking.Backend.TicketBooking.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigurationService {
    private final ConfigurationRepository repository;

    @Autowired
    public ConfigurationService(ConfigurationRepository repository) {
        this.repository = repository;
    }

    public Configuration saveConfiguration(Configuration config) {
        return repository.save(config);
    }

    public Configuration getConfigurationById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Configuration> getAllConfigurations() {
        return repository.findAll();
    }

    public void deleteConfigurationById(String id) {
        repository.deleteById(id);
    }
}
