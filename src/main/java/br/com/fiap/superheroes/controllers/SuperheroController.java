package br.com.fiap.superheroes.controllers;

import br.com.fiap.superheroes.models.Superhero;
import br.com.fiap.superheroes.services.SuperheroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("heroes")
@Slf4j
public class SuperheroController {

    @Autowired
    private SuperheroService service;

    @GetMapping("/all")
    public List<Superhero> listAll(){
        return service.getAllHeroes();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Superhero createHero(@RequestBody Superhero superhero){
        return service.addHero(superhero);
    }

    @GetMapping("/{id}")
    public Superhero getHeroById(@PathVariable Long id){
        log.info("Obtaining hero {} information... ", id);
        var optionalHero = service.getHeroById(id);

        if (optionalHero.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optionalHero.get();
    }
}
