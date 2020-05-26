package com.man.truckapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.man.truckapp.domain.Segment;
import com.man.truckapp.domain.Truck;
import com.man.truckapp.repository.SegmentRepository;
import com.man.truckapp.repository.TruckRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TruckService {

    Logger logger = LoggerFactory.getLogger(TruckService.class);

    private TruckRepository truckRepository;

    private SegmentRepository segmentRepository;

    @Autowired
    public TruckService(TruckRepository repository, SegmentRepository segmentRepository) {
        this.truckRepository = repository;
        this.segmentRepository = segmentRepository;
    }

    public Optional<Truck> save(@Valid Truck truck) {
        try {

            List<Segment> newSegments = truck.getSegments().stream().filter(s -> s.getId() == null)
                    .collect(Collectors.toList());

            if (!newSegments.isEmpty()) {

                truck.getSegments().removeIf(s -> s.getId() == null);

                Optional<List<Segment>> segmentsSaved = Optional.of(segmentRepository.saveAll(newSegments));

                if (segmentsSaved.isPresent()) {
                    segmentsSaved.get().stream().forEach(s -> truck.addSegment(s));
                }
            }

            return Optional.of(truckRepository.save(truck));
        } catch (Exception e) {
            logger.error("Error on save Truck: " + e.getMessage());
        }
        return Optional.empty();
    }
}