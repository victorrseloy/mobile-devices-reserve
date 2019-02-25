package com.beamtrail;

import com.beamtrail.exception.DuplicatedUserException;
import com.beamtrail.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public Application(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

    }


    @Override
    public void run(String... strings) {
        log.info("Attempting to create a beamtrail user");
        try{
            userService.createUser("email@email.com", "password");
        }
        catch (DuplicatedUserException e){
            log.warn("the default user has already been created");
        }

    }
}
