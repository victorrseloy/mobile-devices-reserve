package com.beamtrail.api;

import com.beamtrail.entity.User;
import com.beamtrail.exception.DuplicatedUserException;
import com.beamtrail.repository.UserRepository;
import com.beamtrail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<String> create(String email, String password){
        try{
            userService.createUser(email,password);
            return ResponseEntity.ok("ok");
        }
        catch (DuplicatedUserException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You're not allowed to create an user with this email");
        }

    }


}
