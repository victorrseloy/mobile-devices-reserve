package com.beamtrail.api;

import com.beamtrail.entity.User;
import com.beamtrail.exception.DeviceNotFoundException;
import com.beamtrail.exception.InconsistentPhoneBookingException;
import com.beamtrail.pojo.PhoneDto;
import com.beamtrail.service.PhoneService;
import com.beamtrail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/phone")
public class PhoneController {

    @Autowired
    private  PhoneService phoneService;

    @Autowired
    private  UserService userService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<List<PhoneDto>> getPhones(){
            return ResponseEntity.ok(phoneService.getPhones());
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/book",method = RequestMethod.POST)
    public ResponseEntity<String> bookPhone(Long phoneId){
        User currentUser = userService.getCurrentUser();
        try{
            phoneService.bookPhone(currentUser,phoneId);
            return ResponseEntity.ok("ok");
        } catch (InconsistentPhoneBookingException|DeviceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }

    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/return",method = RequestMethod.POST)
    public ResponseEntity<String> returnPhone(Long phoneId){
        User currentUser = userService.getCurrentUser();
        try{
            phoneService.returnPhone(currentUser,phoneId);
            return ResponseEntity.ok("ok");
        } catch (InconsistentPhoneBookingException|DeviceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }

    }


}
