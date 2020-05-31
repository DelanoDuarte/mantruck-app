package com.man.truckapp.controllers;

import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.man.truckapp.domain.FuelType;
import com.man.truckapp.domain.RangeType;
import com.man.truckapp.domain.Truck;
import com.man.truckapp.repository.TruckRepository;
import com.man.truckapp.service.TruckService;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

                Truck truck = Truck.builder().withModel("TGX 1080").withEnginePower(450)
                                .withFuelType(FuelType.BIO_DIESEL).withRangeType(RangeType.LIGHT_RANGE).build();

                Truck truckSaved = Truck.builder().withModel(truck.getModel()).withEnginePower(truck.getEnginePower())
                                .withFuelType(truck.getFuel()).withRangeType(truck.getRange()).build();

                truckSaved.setId(1L);

                doReturn(ResponseEntity.status(HttpStatus.OK).body(truckSaved)).when(truckController)
                                .save(Mockito.any(Truck.class));

                String truckJson = new ObjectMapper().writeValueAsString(truck);

                mockMvc.perform(MockMvcRequestBuilders.post("/truck").contentType(MediaType.APPLICATION_JSON)
                                .content(truckJson).characterEncoding("UTF-8").accept(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.model").exists())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.enginePower").exists());
        }

        @Test
        public void should_trySaveANewTruckAndReturnErrorMessageWithRequiredFieldsThroughController() throws Exception {

                Truck truck = Truck.builder().withFuelType(FuelType.BIO_DIESEL).build();

                String truckJson = new ObjectMapper().writeValueAsString(truck);

                mockMvc.perform(MockMvcRequestBuilders.post("/truck").contentType(MediaType.APPLICATION_JSON)
                                .content(truckJson).characterEncoding("UTF-8").accept(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.model",
                                                Is.is("The model field is required.")))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.enginePower",
                                                Is.is("The Engine Power field is required.")));
        }

        @Test
        public void should_findATruckByIdThroughController() throws Exception {

                Truck truck = Truck.builder().withModel("TGX 1080").withEnginePower(450)
                                .withFuelType(FuelType.BIO_DIESEL).build();

                truck.setId(1L);

                doReturn(ResponseEntity.status(HttpStatus.OK).body(truck)).when(truckController).findByTruckId(1L);

                mockMvc.perform(MockMvcRequestBuilders.get("/truck/" + 1L).characterEncoding("UTF-8")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.model").exists());

        }

        @Test
        public void should_returnAllTrucksThroughController() throws Exception {

                Truck truck1 = Truck.builder().withModel("TGX 1080").withEnginePower(480).withFuelType(FuelType.DIESEL)
                                .build();
                Truck truck2 = Truck.builder().withModel("TGS 1040").withEnginePower(400)
                                .withFuelType(FuelType.BIO_DIESEL).build();

                truck1.setId(1L);
                truck2.setId(2L);

                List<Truck> trucks = Arrays.asList(truck1, truck2);

                doReturn(ResponseEntity.status(HttpStatus.OK).body(trucks)).when(truckController).findAllTrucks();

                mockMvc.perform(MockMvcRequestBuilders.get("/truck").characterEncoding("UTF-8")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].model").exists())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].model").exists());

        }

        @Test
        public void should_returnAllFuelTypesThroughController() throws Exception {

                doReturn(ResponseEntity.ok(FuelType.values())).when(truckController).retunrAllFuelTypes();

                mockMvc.perform(MockMvcRequestBuilders.get("/truck/fuels").characterEncoding("UTF-8")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.[0]", Is.is("Gasoline")))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.[1]", Is.is("Diesel")));
        }

        @Test
        public void should_returnAllRangeTypesThroughController() throws Exception {

                doReturn(ResponseEntity.ok(RangeType.values())).when(truckController).retunrAllRangeTypes();

                mockMvc.perform(MockMvcRequestBuilders.get("/truck/ranges").characterEncoding("UTF-8")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.[0]", Is.is("Heavy Range")))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.[1]", Is.is("Light Range")));
        }

        @Test
        public void should_returnTrucksPaginatedAndFilterd() throws Exception {

                Truck truck1 = Truck.builder().withModel("TGX 1080").withEnginePower(480).withFuelType(FuelType.DIESEL)
                                .build();
                Truck truck2 = Truck.builder().withModel("TGS 1040").withEnginePower(400)
                                .withFuelType(FuelType.BIO_DIESEL).build();

                truck1.setId(1L);
                truck2.setId(2L);

                Truck searchTruck = Truck.builder().withModel("TG").build();
                String truckJson = new ObjectMapper().writeValueAsString(searchTruck);

                Page<Truck> trucks = new PageImpl<>(Arrays.asList(truck1, truck2));

                doReturn(ResponseEntity.ok(trucks)).when(truckController).findAllFilteredWithPageable(searchTruck, 0,
                                10);

                mockMvc.perform(MockMvcRequestBuilders.post("/truck/find?page=0&size=10").characterEncoding("UTF-8")
                                .content(truckJson).contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isOk());
        }
}