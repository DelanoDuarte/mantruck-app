package com.man.truckapp.service;

import java.util.Optional;

import javax.validation.Valid;

import com.man.truckapp.domain.Truck;
import com.man.truckapp.repository.TruckRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TruckService {

    Logger logger = LoggerFactory.getLogger(TruckService.class);

    private TruckRepository truckRepository;

    @Autowired
    public TruckService(TruckRepository repository) {
        this.truckRepository = repository;
    }

    public Optional<Truck> save(@Valid Truck truck) {
        try {
            return Optional.of(truckRepository.save(truck));
        } catch (Exception e) {
            logger.error("Error on save Truck: " + e.getMessage());
        }
        return Optional.empty();
    }
}