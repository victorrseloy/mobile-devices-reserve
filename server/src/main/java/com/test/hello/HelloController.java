package com.test.hello;

import com.test.entity.Superhero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.test.repository.SuperheroRepository;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private SuperheroRepository repository;

    @RequestMapping("/")
    public String index() {

        Superhero superhero = new Superhero();
        superhero.setFirstName("test");
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
