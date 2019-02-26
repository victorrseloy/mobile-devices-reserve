package com.beamtrail.pojo;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * Dto that holds phone entity related info, one of the major reasons to use Dtos like this and avoid
 * to return entities directly from the controllers is the problem with potential infinite loops that
 * might occur during the JSON generation of the entity
 *
 * example Phone -> PhoneBooking -> Phone
 *
 * as the navigation is bidirection converting this entity to a JSON will cause a loop if a phone booking
 * exists for the given device. One way to solve this and send only the required data to the user
 * is using dtos like this one
 */
@Data
@Builder
public class PhoneDto {
    private long id;
    private String brand;
    private String name;
    private boolean available;
    private String currentUser;
    private PhoneCapabilitiesDto phoneCapabilities;
    private Instant bookedDate;

}
