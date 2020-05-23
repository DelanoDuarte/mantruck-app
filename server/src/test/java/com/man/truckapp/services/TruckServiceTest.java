package com.man.truckapp.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.man.truckapp.domain.FuelType;
import com.man.truckapp.domain.Truck;
import com.man.truckapp.repository.TruckRepository;
import com.man.truckapp.service.TruckService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TruckServiceTest
 */

@RunWith(SpringRunner.class)
public class TruckServiceTest {

    @Mock
    private TruckRepository truckRepository;

    @InjectMocks
    private TruckService truckService;

    @Before
    public void init() {
        truckService = new TruckService(truckRepository);
    }

    @Test
    public void should_saveANewTruck() {
        Truck truck = Truck.builder().withModel("Model Class A").withEnginePower(340).withFuelType(FuelType.DIESEL)
                .build();

        Truck truckToReturn = truck;
        truckToReturn.setId(1L);

        Mockito.when(truckRepository.save(truck)).thenReturn(truckToReturn);
        Optional<Truck> truckAfterSave = truckService.save(truck);

        assertTrue(truckAfterSave.isPresent());
    }
}