package com.mobilebook.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * Dto for the phone capabilities
 */
@Data
@Builder
public class PhoneCapabilitiesDto {
    private String technology;
    private String bands2g;
    private String bands3g;
    private String bands4g;
}
