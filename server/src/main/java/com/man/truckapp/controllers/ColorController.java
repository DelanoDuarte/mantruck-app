package com.man.truckapp.controllers;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.MediaType;

import com.man.truckapp.domain.Color;
import com.man.truckapp.repository.ColorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/color", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
public class ColorController {

    private ColorRepository colorRepository;

    @Autowired
    public ColorController(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @GetMapping
    public ResponseEntity<List<Color>> findAllColorsPaginatedByDescription(@RequestParam("color") String color) {

        Optional<List<Color>> colors = Optional.of(colorRepository.findByColorContaining(color, PageRequest.of(0, 3)));

        if (colors.isPresent()) {
            if (colors.get().size() > 0)
                return ResponseEntity.ok(colors.get());
            else
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}