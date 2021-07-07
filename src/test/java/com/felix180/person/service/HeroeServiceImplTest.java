package com.felix180.person.service;

import com.felix180.person.data.HeroeDataTest;
import com.felix180.person.entity.Heroe;
import com.felix180.person.repository.HeroeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static com.felix180.person.data.HeroeDataTest.ID_1;
import static com.felix180.person.data.HeroeDataTest.getHeroeSuperMan;

@SpringBootTest
class HeroeServiceImplTest {

  public static final String QUERY_SUPER = "Super";
  public static final String NOT_SUPER = "NotSuper";
  @MockBean HeroeRepository heroeRepository;

  @Autowired HeroeService heroeService;

  @BeforeEach
  void setUp() {
    Mockito.when(heroeRepository.findAll()).thenReturn(HeroeDataTest.getHeroeList());
    Mockito.when(heroeRepository.findById(ID_1))
        .thenReturn(java.util.Optional.of(HeroeDataTest.getHeroeSuperMan()));
    Mockito.when(heroeRepository.findByName(QUERY_SUPER))
        .thenReturn(HeroeDataTest.getHeroeList());

    Mockito.when(heroeRepository.save(ArgumentMatchers.any()))
        .thenAnswer(
            (Answer<Heroe>)
                invocationOnMock -> {
                  Object[] args = invocationOnMock.getArguments();
                  return (Heroe) args[0];
                })
        .thenReturn(HeroeDataTest.getHeroeSuperMan());
    Mockito.when(heroeRepository.getById(ID_1))
            .thenReturn(HeroeDataTest.getHeroeSuperMan());

  //  Mockito.when(heroeRepository.delete(HeroeDataTest.getHeroeSpiderman())).


  }

  @AfterEach
  void tearDown() {}

  @Test
  void getAll() {

    List<Heroe> result = heroeService.getAll();
    Assertions.assertEquals(HeroeDataTest.getHeroeList(), result);
  }

  @Test
  void findById() {
    Heroe result = heroeService.findById(ID_1);
    Assertions.assertEquals(HeroeDataTest.getHeroeSuperMan(), result);
  }

  @Test
  void findByName() {
    List<Heroe> result = heroeService.findByName(QUERY_SUPER);
    Assertions.assertEquals(HeroeDataTest.getHeroeList(), result);
  }

  @Test
  void create() {}

  @Test
  void update() {
    Heroe heroeToUpdate = getHeroeSuperMan();
    heroeToUpdate.setName(NOT_SUPER);

    Heroe result = heroeService.update(heroeToUpdate);
    Assertions.assertEquals(NOT_SUPER,result.getName());

  }

  @Test
  void delete() {
   Assertions.assertEquals(Boolean.TRUE, heroeService.delete(ID_1));
  }
}
