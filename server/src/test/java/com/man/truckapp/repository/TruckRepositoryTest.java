package com.man.truckapp.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.man.truckapp.domain.FuelType;
import com.man.truckapp.domain.Segment;
import com.man.truckapp.domain.Truck;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TruckRepositoryTest {

    @Autowired
    private TruckRepository truckRepository;

    @Test
    public void should_saveANewTruckInDatabase() {
        Truck truck = this.truckBuilder();
        Truck savedTruck = truckRepository.saveAndFlush(truck);
        assertTrue(truckRepository.existsById(savedTruck.getId()));
    }

    @Test
    public void should_findAExistingTruckInDatabase() {
        Truck truck = this.truckBuilder();
        Truck savedTruck = truckRepository.saveAndFlush(truck);

        Truck findedTruck = truckRepository.getOne(savedTruck.getId());

        assertTrue(findedTruck.getModel().equalsIgnoreCase(savedTruck.getModel()));
    }

    @Test
    public void should_deleteAExistingTruckInDatabase() {
        Truck truck = this.truckBuilder();
        Truck savedTruck = truckRepository.saveAndFlush(truck);

        truckRepository.delete(savedTruck);

        assertTrue(!truckRepository.existsById(savedTruck.getId()));
    }

    @Test
    public void should_saveATruckWithSegments(){
        Truck truck = this.truckBuilderWithSegments();
        Truck savedTruck = truckRepository.saveAndFlush(truck);

        assertTrue(savedTruck.getSegements().size() == 1);
    }

    private Truck truckBuilder() {
        Truck truck = Truck.builder().withName("TGS 1870").withEnginePower(440).withFuelType(FuelType.DIESEL).build();
        return truck;
    }

    private Truck truckBuilderWithSegments() {
        Truck truck = Truck.builder().withName("TGS 1870").withEnginePower(440).withFuelType(FuelType.DIESEL)
                .addSegment(new Segment("Wastedisposal"))
                .build();
        return truck;
    }
}