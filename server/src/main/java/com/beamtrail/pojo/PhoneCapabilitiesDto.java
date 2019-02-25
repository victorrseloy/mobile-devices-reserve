package com.beamtrail.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneCapabilitiesDto {
    private String technology;
    private String bands2g;
    private String bands3g;
    private String bands4g;
}
