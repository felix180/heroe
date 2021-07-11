package com.felix180.person.controller;

import com.felix180.person.entity.Heroe;
import com.felix180.person.service.HeroeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController("heroe")
@Api(value = "Heroes")
public class HeroeController {

  @Autowired private HeroeService heroeService;

  @ApiOperation(value = "View a list of available heroes", response = List.class)
  @GetMapping("/getAll")
  public List<Heroe> getAll() {
    return heroeService.getAll();
  }

  @ApiOperation(value = "find heroes by id", response = Heroe.class)
  @GetMapping("/findById/{id}")
  public Heroe findById(@PathVariable("id") Long id) {
    return heroeService.findById(id);
  }

  @ApiOperation(value = "find heroes by name", response = Heroe.class)
  @GetMapping("/findByName/{name}")
  public List<Heroe> findByName(@PathVariable("name") String name) {
    return heroeService.findByName(name);
  }

  @PostMapping("/create")
  public Heroe create(@RequestBody Heroe heroe) {
    return heroeService.create(heroe);
  }

  @ApiOperation(value = "update heroe by body", response = Heroe.class)
  @PutMapping("/update")
  public Heroe update(@RequestBody Heroe heroe) {
    return heroeService.update(heroe);
  }

  @ApiOperation(value = "delate heroe by id", response = Heroe.class)
  @DeleteMapping("/delete/{id}")
  public Boolean delete(@PathVariable("id") Long id) {
    return heroeService.delete(id);
  }

  @ApiIgnore
  @RequestMapping(value = "/")
  public void redirect(HttpServletResponse response) throws IOException {
    response.sendRedirect("/swagger-ui.html");
  }
}
