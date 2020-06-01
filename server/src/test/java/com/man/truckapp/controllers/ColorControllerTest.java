package com.man.truckapp.controllers;

import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.man.truckapp.domain.Color;

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
@WebMvcTest(ColorController.class)
public class ColorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ColorController colorController;

    @Test
    public void should_returnAllColorsByColorDescriptionPaginated() throws Exception {

        Color color1 = Color.builder().withColor("Green").build();
        color1.setId(1L);

        Color color2 = Color.builder().withColor("Gray").build();
        color2.setId(2L);

        List<Color> colors = Arrays.asList(color1, color2);

        doReturn(ResponseEntity.status(HttpStatus.OK).body(colors)).when(colorController)
                .findAllColorsPaginatedByDescription("Gr");

        mockMvc.perform(MockMvcRequestBuilders.get("/color").param("color", "Gr")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", Is.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id", Is.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].color", Is.is("Green")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].color", Is.is("Gray")));
    }
}