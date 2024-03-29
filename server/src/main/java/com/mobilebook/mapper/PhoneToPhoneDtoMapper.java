package com.mobilebook.mapper;

import com.aafanasev.fonoapi.DeviceEntity;
import com.mobilebook.entity.Phone;
import com.mobilebook.entity.PhoneBooking;
import com.mobilebook.pojo.PhoneDto;

import java.util.Comparator;

/**
 *  Simple mapper that maps from a Phone to a PhoneDto
 */
public class PhoneToPhoneDtoMapper {

    /**
     *
     * maps a phone to a PhoneDto
     *
     * @param phone a phone to be mapped
     * @param deviceEntity a device entity related to the phone being mapped
     * @return the corresponding PhoneDto
     */
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

    /**
     *
     * maps a phone to a PhoneDto
     *
     * @param phone a phone to be mapped
     * @return the corresponding PhoneDto
     */
    public static PhoneDto map(Phone phone){

        PhoneDto dto = PhoneToPhoneDtoMapper.map(phone,null);
        dto.setPhoneCapabilities(phone.getCapabilities());
        return dto;

    }
}
