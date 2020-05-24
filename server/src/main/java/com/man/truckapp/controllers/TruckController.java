package com.man.truckapp.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import com.man.truckapp.domain.FuelType;
import com.man.truckapp.domain.Truck;
import com.man.truckapp.repository.TruckRepository;
import com.man.truckapp.service.TruckService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/truck", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
public class TruckController {

    Logger logger = LoggerFactory.getLogger(TruckController.class);

    private TruckRepository truckRepository;
    private TruckService truckService;

    @Autowired
    public TruckController(TruckService truckService, TruckRepository truckRepository) {
        this.truckService = truckService;
        this.truckRepository = truckRepository;
    }

    @PostMapping
    public ResponseEntity<Truck> save(@RequestBody @Valid Truck truck) {
        try {
            Optional<Truck> savedTruck = truckService.save(truck);
            if (savedTruck.isPresent()) {
                return ResponseEntity.ok(savedTruck.get());
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            logger.error("Error on Trunk Controller save method: " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Truck> findByTruckId(@PathVariable("id") Long id) {
        Optional<Truck> truck = truckRepository.findById(id);
        if (truck.isPresent()) {
            return ResponseEntity.ok(truck.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Truck>> findAllTrucks() {
        try {
            Optional<List<Truck>> allTrucks = Optional.of(truckRepository.findAll());
            if (allTrucks.isPresent()) {
                if (allTrucks.get().size() > 0)
                    return ResponseEntity.ok(allTrucks.get());
                else
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            logger.error("Error on find all trucks method on controller: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/fuels")
    public ResponseEntity<FuelType[]> retunrAllFuelTypes() {
        return ResponseEntity.ok(FuelType.values());
    }
}