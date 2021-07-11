package com.felix180.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felix180.person.entity.Heroe;
import com.felix180.person.repository.HeroeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PersonApplication.class)
@AutoConfigureMockMvc
public class TestIntegration {

  public static final String TEST_SUPER = "TestSuper";

  @Autowired private MockMvc mvc;

  @Autowired private HeroeRepository repository;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void createFindDeleteHeroe() throws Exception {

    Heroe heroe = new Heroe(30l, TEST_SUPER);
    MvcResult resultActions =
        mvc.perform(
                post("/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(heroe)))
            .andExpect(status().isOk())
            .andReturn();

    Heroe resultCreate = parseResponse(resultActions, Heroe.class);

    Heroe heroeResult = repository.findByName(TEST_SUPER).get(0);
    Assertions.assertEquals(heroeResult.getName(), TEST_SUPER);

    mvc.perform(
            delete("/delete/" + resultCreate.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(heroe)))
            .andExpect(status().isOk());

    mvc.perform(get("/findById" + resultCreate.getId()).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  private <T> T parseResponse(MvcResult result, Class<T> responseClass) {
    try {
      String contentAsString = result.getResponse().getContentAsString();
      return objectMapper.readValue(contentAsString, responseClass);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
