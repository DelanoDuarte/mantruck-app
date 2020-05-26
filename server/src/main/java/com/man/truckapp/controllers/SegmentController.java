package com.man.truckapp.controllers;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.MediaType;

import com.man.truckapp.domain.Segment;
import com.man.truckapp.repository.SegmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/segment", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
public class SegmentController {

    private SegmentRepository segmentRepository;

    @Autowired
    public SegmentController(SegmentRepository segmentRepository) {
        this.segmentRepository = segmentRepository;
    }

    @GetMapping
    public ResponseEntity<List<Segment>> findAllSegmentsPaginatedByDescription(
            @RequestParam("description") String description) {

        Optional<List<Segment>> segments = Optional
                .of(segmentRepository.findByDescriptionContaining(description, PageRequest.of(0, 3)));

        if (segments.isPresent()) {
            if (segments.get().size() > 0)
                return ResponseEntity.ok(segments.get());
            else
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}