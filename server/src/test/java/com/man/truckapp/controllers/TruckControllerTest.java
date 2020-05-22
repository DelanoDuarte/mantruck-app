package com.man.truckapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.man.truckapp.domain.FuelType;
import com.man.truckapp.domain.Truck;
import com.man.truckapp.repository.TruckRepository;
import com.man.truckapp.service.TruckService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(TruckController.class)
public class TruckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TruckRepository truckRepository;

    @MockBean
    private TruckService truckService;

    @MockBean
    private TruckController truckController;

    @Before
    public void setUp() {
        truckController = new TruckController(truckService, truckRepository);
    }

    @Test
    public void should_saveNewTruckByController() throws Exception {

        Truck truck = Truck.builder().withName("TGX 1080").withEnginePower(450).withFuelType(FuelType.BIO_DIESEL)
                .build();

        String truckJson = new ObjectMapper().writeValueAsString(truck);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/truck")
                .contentType(MediaType.APPLICATION_JSON)
                .content(truckJson)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}