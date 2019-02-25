package com.beamtrail.pojo;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

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
