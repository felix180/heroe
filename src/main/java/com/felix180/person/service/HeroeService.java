package com.felix180.person.service;

import com.felix180.person.entity.Heroe;

import java.util.List;

public interface HeroeService {

    List<Heroe> getAll();

    Heroe findById(Long id);

    List<Heroe> findByName(String name);

    Heroe create(Heroe heroe);

    Heroe update(Heroe heroe);

    Boolean delete(Long id);
}
