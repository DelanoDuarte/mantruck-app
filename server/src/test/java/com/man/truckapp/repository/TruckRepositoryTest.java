package com.man.truckapp.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.man.truckapp.domain.FuelType;
import com.man.truckapp.domain.RangeType;
import com.man.truckapp.domain.Truck;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TruckRepositoryTest {

    @Autowired
    private TruckRepository truckRepository;

    @Before
    public void beforeTest() {
        Truck truck1 = Truck.builder().withModel("TGS1870").withEnginePower(440).withFuelType(FuelType.DIESEL)
                .withRangeType(RangeType.LIGHT_RANGE).build();

        truckRepository.saveAndFlush(truck1);
    }

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
    public void should_findAllTrucksWithFilter() {

        Truck searchTruck = Truck.builder().withModel("TGS").withFuelType(FuelType.DIESEL).build();

        ExampleMatcher customExampleMatcher = ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues()
                .withMatcher("model", match -> match.contains().ignoreCase());

        Optional<Page<Truck>> trucksFiltered = Optional
                .of(truckRepository.findAll(Example.of(searchTruck, customExampleMatcher), PageRequest.of(0, 10)));

        assertTrue(trucksFiltered.get().getContent().size() == 1);
    }

    private Truck truckBuilder() {
        Truck truck = Truck.builder().withModel("TGS 1870").withEnginePower(440).withFuelType(FuelType.DIESEL)
                .withRangeType(RangeType.LIGHT_RANGE).build();
        return truck;
    }

}