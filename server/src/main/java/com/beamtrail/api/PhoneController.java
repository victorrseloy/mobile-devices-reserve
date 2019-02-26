package com.beamtrail.api;

import com.beamtrail.entity.User;
import com.beamtrail.exception.DeviceNotFoundException;
import com.beamtrail.exception.InconsistentPhoneBookingException;
import com.beamtrail.pojo.PhoneDto;
import com.beamtrail.service.PhoneBookingService;
import com.beamtrail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller handles phone related operations, like listing
 * booking and returning a device
 */
@RestController
@RequestMapping("/api/v1/phone")
public class PhoneController {

    @Autowired
    private PhoneBookingService phoneBookingService;

    @Autowired
    private  UserService userService;

    /**
     * returns a list containing information about all the devices
     *
     * @return A list of phone dtos with devices details
     */
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<List<PhoneDto>> getPhones(){

            return ResponseEntity.ok(phoneBookingService.getPhones());

    }

    /**
     *
     * Book a phone for a user
     *
     * @param phoneId the phone id being booked
     * @return - an http status representing the transaction
     */
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/book",method = RequestMethod.POST)
    public ResponseEntity<String> bookPhone(Long phoneId){

        User currentUser = userService.getCurrentUser();

        try{

            phoneBookingService.bookPhone(currentUser,phoneId);
            return ResponseEntity.ok("ok");

        } catch (InconsistentPhoneBookingException | DeviceNotFoundException e) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());

        }

    }

    /**
     * returned a booked phone by a user
     *
     * @param phoneId the phone id being returned
     * @return @return - an http status representing the transaction
     */
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/return",method = RequestMethod.POST)
    public ResponseEntity<String> returnPhone(Long phoneId){
        User currentUser = userService.getCurrentUser();

        try{

            phoneBookingService.returnPhone(currentUser,phoneId);
            return ResponseEntity.ok("ok");

        } catch (InconsistentPhoneBookingException|DeviceNotFoundException e) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }

    }


}
