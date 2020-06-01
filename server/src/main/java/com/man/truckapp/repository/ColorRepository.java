package com.man.truckapp.repository;

import java.util.List;

import com.man.truckapp.domain.Color;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    List<Color> findByColorContaining(String color, Pageable pageable);

}