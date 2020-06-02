package com.man.truckapp.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Optional;

import com.man.truckapp.domain.Color;
import com.man.truckapp.domain.FuelType;
import com.man.truckapp.domain.Segment;
import com.man.truckapp.domain.Truck;
import com.man.truckapp.repository.ColorRepository;
import com.man.truckapp.repository.SegmentRepository;
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

    @Mock
    private SegmentRepository segmentRepository;

    @Mock
    private ColorRepository colorRepository;

    @InjectMocks
    private TruckService truckService;

    @Before
    public void init() {
        truckService = new TruckService(truckRepository, segmentRepository, colorRepository);
    }

    @Test
    public void should_saveANewTruck() {
        Truck truck = Truck.builder().withModel("Model Class A").withEnginePower(340).withFuelType(FuelType.DIESEL)
                .addOneSegment(new Segment("Food")).build();

        Truck truckToReturn = truck;
        truckToReturn.setId(1L);

        Mockito.when(truckRepository.save(truck)).thenReturn(truckToReturn);
        Optional<Truck> truckAfterSave = truckService.save(truck);

        assertTrue(truckAfterSave.isPresent());
    }

    @Test
    public void should_saveANewTruckWithNewSegmentsAndExistingSegments() {

        Segment segment1 = new Segment("Food");
        Segment segment2 = new Segment("Wastedisposal");

        segment2.setId(1L);

        Segment segmentSaved = new Segment(2L, segment1.getDescription());

        Truck truck = Truck.builder().withModel("Model Class A").withEnginePower(340).withFuelType(FuelType.DIESEL)
                .addOneSegment(segment1).addOneSegment(segment2).build();

        Truck truckToReturn = truck;
        truckToReturn.setId(1L);

        Mockito.when(segmentRepository.saveAll(Arrays.asList(segment1))).thenReturn(Arrays.asList(segmentSaved));

        Mockito.when(truckRepository.save(truck)).thenReturn(truckToReturn);
        Optional<Truck> truckAfterSave = truckService.save(truck);

        assertTrue(truckAfterSave.isPresent());
        assertTrue(truckAfterSave.get().getSegments().size() == 2);
    }

    @Test
    public void should_saveANewTruckWithNewColorsAndExistingColors() {

        Color blue = new Color("Blue");
        Color red = new Color("Red");

        red.setId(1L);

        Color colorsSaved = new Color(2L, blue.getColor());

        Truck truck = Truck.builder().withModel("Model Class A").withEnginePower(340).withFuelType(FuelType.DIESEL)
                .addOneColor(blue).addOneColor(red).build();

        Truck truckToReturn = truck;
        truckToReturn.setId(1L);

        Mockito.when(colorRepository.saveAll(Arrays.asList(blue))).thenReturn(Arrays.asList(colorsSaved));

        Mockito.when(truckRepository.save(truck)).thenReturn(truckToReturn);
        Optional<Truck> truckAfterSave = truckService.save(truck);

        assertTrue(truckAfterSave.isPresent());
        assertTrue(truckAfterSave.get().getColors().size() == 2);
    }
}