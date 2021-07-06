package com.felix180.person.service;

import com.felix180.person.entity.Heroe;
import com.felix180.person.repository.HeroeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroeServiceImpl implements HeroeService {
    @Autowired
    private HeroeRepository heroeRepository;


    @Override
    public List<Heroe> getAll() {
        return heroeRepository.findAll();
    }

    @Override
    public Heroe findById(Long id) {
        Optional<Heroe> byId = heroeRepository.findById(id);
        return byId.orElse(new Heroe());
    }

    @Override
    public Heroe findByName(String name) {
        return heroeRepository.findByName(name);
    }

    @Override
    public Heroe create(Heroe heroe) {
        return heroeRepository.save(heroe);
    }

    @Override
    public Heroe update(Heroe heroe) {
        Heroe heroeToUpdate = heroeRepository.getById(heroe.getId());
        heroeToUpdate.setName(heroe.getName());
        return heroeRepository.save(heroeToUpdate);
    }

    @Override
    public Boolean delete(Long id) {

        heroeRepository.deleteById(id);
        return true;
    }
}
