package com.beamtrail.mapper;

import com.aafanasev.fonoapi.DeviceEntity;
import com.beamtrail.entity.Phone;
import com.beamtrail.entity.PhoneBooking;
import com.beamtrail.pojo.PhoneDto;

import java.util.Comparator;

public class PhoneToPhoneDtoMapper {
    public static PhoneDto map(Phone phone, DeviceEntity deviceEntity){
        if (phone == null){
            return null;
        }
        PhoneDto.PhoneDtoBuilder builder = PhoneDto.builder();
        builder.available(phone.isAvailable())
                .id(phone.getId())
                .brand(phone.getBrand())
                .name(phone.getDeviceName())
                .phoneCapabilities(DeviceEntityToPhoneCapabilitiesDtoMapper.map(deviceEntity));

        if(!phone.isAvailable()){
            PhoneBooking booking = phone.getPhoneBookings().stream()
                    .max(Comparator.comparing(PhoneBooking::getId)).orElse(null);
            if(booking!=null){
                builder.bookedDate(booking.getBooked());
                builder.currentUser(booking.getUser().getEmail());
            }

        }

        return builder.build();
    }

    public static PhoneDto map(Phone phone){
        PhoneDto dto = PhoneToPhoneDtoMapper.map(phone,null);
        dto.setPhoneCapabilities(phone.getCapabilities());
        return dto;
    }
}
