package com.beamtrail.hello;

import com.beamtrail.entity.Superhero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.beamtrail.repository.SuperheroRepository;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private SuperheroRepository repository;

    @Secured("USER")
    @RequestMapping("/")
    public String index() {

        Superhero superhero = new Superhero();
        superhero.setFirstName("beamtrail");
        superhero.setGood(true);
        superhero.setLastName("hhh");
        superhero.setName("none");
        repository.saveAndFlush(superhero);


        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/get")
    public List<Superhero> get() {

        return repository.findAll();
    }
    
}
