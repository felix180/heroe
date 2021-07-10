package com.felix180.person.exception;

import com.felix180.person.controller.HeroeController;
import com.felix180.person.repository.HeroeRepository;
import com.felix180.person.service.HeroeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({HeroeController.class, HeroeService.class})
class ExceptionTest {

  public static final String NOT_FOUND_ID_4 = "Not found id 4";
  @Autowired private MockMvc mvc;

  @MockBean private HeroeRepository heroeRepository;

  @Test
  void getFindErrorMessageNotFound() throws Exception {
    Mockito.when(heroeRepository.findById(4L)).thenReturn(Optional.empty());

    mvc.perform(get("/findById/4").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message", is(NOT_FOUND_ID_4)));
  }
}
