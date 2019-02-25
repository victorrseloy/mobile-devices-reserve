package com.beamtrail.hello;

import com.beamtrail.entity.Phone;
import com.beamtrail.entity.Superhero;
import com.beamtrail.entity.User;
import com.beamtrail.repository.PhoneRepository;
import com.beamtrail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.beamtrail.repository.SuperheroRepository;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private SuperheroRepository repository;

    @Autowired
    private PhoneRepository phoneRepository;

    private UserService userService;

    @Autowired
    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/")
    public String index() {

        Superhero superhero = Superhero.builder().firstName("beamtrail").good(true).lastName("hhh").name("none").build();
        repository.saveAndFlush(superhero);


        return "Greetings from Spring Boot!";
    }



    @RequestMapping("/phone")
    public ResponseEntity<String> createBooking(){
        Phone phone = Phone.builder().available(true).deviceName("test").build();
        phoneRepository.saveAndFlush(phone);
        return ResponseEntity.ok("success");

    }

    @RequestMapping("/get")
    public List<Superhero> get(@AuthenticationPrincipal User user) {
        userService.getCurrentUser();
        return repository.findAll();
    }
    
}
