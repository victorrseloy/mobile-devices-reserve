package com.beamtrail.mapper;

import com.aafanasev.fonoapi.DeviceEntity;
import com.beamtrail.pojo.PhoneCapabilitiesDto;

/**
 * Simple mapper that maps from a DeviceEntity to a CapabilitiesDto
 */
public class DeviceEntityToPhoneCapabilitiesDtoMapper {

    /**
     * @param deviceEntity - object to be mapped
     * @return the CapabilitiesDto mapped from a given deviceEntity
     */

    public static PhoneCapabilitiesDto map(DeviceEntity deviceEntity){
        if(deviceEntity == null){
            return null;
        }
        return PhoneCapabilitiesDto.builder()
                .technology(deviceEntity.getTechnology())
                .bands2g(deviceEntity.get_2g_bands())
                .bands3g(deviceEntity.get_3g_bands())
                .bands4g(deviceEntity.get_4g_bands())
                .build();
    }
}
