package com.mobilebook;

import com.mobilebook.exception.DuplicatedUserException;
import com.mobilebook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... strings) {
        log.info("Attempting to create a default user");
        try{
            userService.createUser("admin@mobilebook.com", "password");
        }
        catch (DuplicatedUserException e){
            log.warn("the default user has already been created");
        }

    }
}
