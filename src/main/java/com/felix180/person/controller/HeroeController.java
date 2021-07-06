package com.felix180.person.controller;

import com.felix180.person.entity.Heroe;
import com.felix180.person.service.HeroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("heroe")
public class HeroeController {

    @Autowired
    private HeroeService heroeService;

    @GetMapping("/getAll")
    public List<Heroe> getAll() {
        return heroeService.getAll();
    }

    @GetMapping("/findById/{id}")
    public Heroe findById(@PathVariable("id") Long id) {
        return heroeService.findById(id);
    }

    @GetMapping("/findByName/{name}")
    public Heroe findByName(@PathVariable("name") String name) {
        return heroeService.findByName(name);
    }

    @PostMapping("/create")
    public Heroe create(@RequestBody Heroe heroe) {
        return heroeService.create(heroe);
    }

    @PutMapping("/update")
    public Heroe update(@RequestBody Heroe heroe) {
        return heroeService.update(heroe);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return heroeService.delete(id);
    }

}
