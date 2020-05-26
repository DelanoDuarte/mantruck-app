package com.man.truckapp.repository;

import java.util.List;

import com.man.truckapp.domain.Segment;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentRepository extends JpaRepository<Segment, Long> {
    
    List<Segment> findByDescriptionContaining(String description, Pageable pageable);

}