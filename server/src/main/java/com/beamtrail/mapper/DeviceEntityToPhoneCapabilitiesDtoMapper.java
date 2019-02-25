package com.beamtrail.mapper;

import com.aafanasev.fonoapi.DeviceEntity;
import com.beamtrail.pojo.PhoneCapabilitiesDto;

public class DeviceEntityToPhoneCapabilitiesDtoMapper {
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
