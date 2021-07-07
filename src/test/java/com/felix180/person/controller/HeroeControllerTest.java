package com.felix180.person.controller;

import com.felix180.person.data.HeroeDataTest;
import com.felix180.person.service.HeroeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HeroeController.class)
class HeroeControllerTest {

  @Autowired private MockMvc mvc;

  @MockBean private HeroeService service;

  @Test
  void getAll() throws Exception {
    Mockito.when(service.getAll()).thenReturn(HeroeDataTest.getHeroeList());

    mvc.perform(get("/getAll").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect( jsonPath("$[0].name", is(HeroeDataTest.getHeroeSuperMan().getName())));
  }
}
