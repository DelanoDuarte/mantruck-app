package com.man.truckapp.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import com.man.truckapp.domain.FuelType;
import com.man.truckapp.domain.RangeType;
import com.man.truckapp.domain.Truck;
import com.man.truckapp.repository.TruckRepository;
import com.man.truckapp.service.TruckService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteATruck(@PathVariable("id") Long id) {
        truckRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fuels")
    public ResponseEntity<FuelType[]> retunrAllFuelTypes() {
        return ResponseEntity.ok(FuelType.values());
    }

    @GetMapping("/ranges")
    public ResponseEntity<RangeType[]> retunrAllRangeTypes() {
        return ResponseEntity.ok(RangeType.values());
    }

    @PostMapping("/find")
    public ResponseEntity<Page<Truck>> findAllFilteredWithPageable(@RequestBody(required = false) Truck truck,
            @RequestParam("page") int page, @RequestParam("size") int size) {

        ExampleMatcher customExampleMatcher = ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues()
                .withMatcher("model", match -> match.contains().ignoreCase());

        Optional<Page<Truck>> trucksFiltered = Optional
                .of(truckRepository.findAll(Example.of(truck, customExampleMatcher), PageRequest.of(page, size)));

        if (trucksFiltered.isPresent()) {
            if (!trucksFiltered.get().isEmpty())
                return ResponseEntity.ok(trucksFiltered.get());
            else
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Truck>> findAllTrucksPaginated(@RequestParam("page") int page,
            @RequestParam("size") int size) {
        try {
            Optional<Page<Truck>> allTrucks = Optional.of(truckRepository.findAll(PageRequest.of(page, size)));
            if (allTrucks.isPresent()) {
                return ResponseEntity.ok(allTrucks.get());
            }
        } catch (Exception e) {
            logger.error("Error on find all trucks paginated method on controller: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}