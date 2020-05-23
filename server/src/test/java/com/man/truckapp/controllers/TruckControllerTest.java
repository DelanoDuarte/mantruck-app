package com.man.truckapp.controllers;

import static org.mockito.Mockito.doReturn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.man.truckapp.domain.FuelType;
import com.man.truckapp.domain.Truck;
import com.man.truckapp.repository.TruckRepository;
import com.man.truckapp.service.TruckService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
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

    @Test
    public void should_saveNewTruckThroughController() throws Exception {

        Truck truck = Truck.builder().withModel("TGX 1080").withEnginePower(450).withFuelType(FuelType.BIO_DIESEL)
                .build();

        Truck truckSaved = Truck.builder().withModel(truck.getModel()).withEnginePower(truck.getEnginePower())
                .withFuelType(truck.getFuel()).build();

        truckSaved.setId(1L);

        doReturn(ResponseEntity.status(HttpStatus.OK).body(truckSaved)).when(truckController)
                .save(Mockito.any(Truck.class));

        String truckJson = new ObjectMapper().writeValueAsString(truck);

        mockMvc.perform(MockMvcRequestBuilders.post("/truck").contentType(MediaType.APPLICATION_JSON).content(truckJson)
                .characterEncoding("UTF-8").accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.enginePower").exists());
    }

    @Test
    public void should_findATruckByIdThroughController() throws Exception {

        Truck truck = Truck.builder().withModel("TGX 1080").withEnginePower(450).withFuelType(FuelType.BIO_DIESEL)
                .build();

        truck.setId(1L);

        doReturn(ResponseEntity.status(HttpStatus.OK).body(truck)).when(truckController).findByTruckId(1L);

        mockMvc.perform(MockMvcRequestBuilders.get("/truck/" + 1L).characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").exists());

    }
}