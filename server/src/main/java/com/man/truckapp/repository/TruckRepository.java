package com.man.truckapp.repository;

import com.man.truckapp.domain.Truck;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long>, JpaSpecificationExecutor<Truck> {

}