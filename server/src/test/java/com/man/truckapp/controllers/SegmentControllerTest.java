package com.man.truckapp.controllers;

import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.man.truckapp.domain.Segment;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(SegmentController.class)
public class SegmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SegmentController segmentController;

    @Test
    public void should_returnAllSegmentsByDescriptionPaginated() throws Exception {

        Segment segment1 = Segment.builder().withDescription("Water Truck").build();
        segment1.setId(1L);

        Segment segment2 = Segment.builder().withDescription("Wastedisposal").build();
        segment2.setId(2L);

        List<Segment> segments = Arrays.asList(segment1, segment2);

        doReturn(ResponseEntity.status(HttpStatus.OK).body(segments)).when(segmentController)
                .findAllSegmentsPaginatedByDescription("Wa");

        mockMvc.perform(MockMvcRequestBuilders.get("/segment").param("description", "Wa")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", Is.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id", Is.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].description", Is.is("Water Truck")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].description", Is.is("Wastedisposal")));
    }
}