package com.beamtrail.service;

import com.aafanasev.fonoapi.DeviceEntity;
import com.aafanasev.fonoapi.retrofit.FonoApiFactory;
import com.aafanasev.fonoapi.retrofit.FonoApiService;
import com.beamtrail.entity.Phone;
import com.beamtrail.entity.PhoneBooking;
import com.beamtrail.entity.User;
import com.beamtrail.exception.DeviceNotFoundException;
import com.beamtrail.exception.InconsistentPhoneBookingException;
import com.beamtrail.mapper.DeviceEntityToPhoneCapabilitiesDtoMapper;
import com.beamtrail.mapper.PhoneToPhoneDtoMapper;
import com.beamtrail.pojo.PhoneDto;
import com.beamtrail.repository.PhoneBookingRepository;
import com.beamtrail.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhoneService {

    FonoApiService api = new FonoApiFactory().create();
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private PhoneBookingRepository phoneBookingRepository;


    public List<PhoneDto> getPhones() {
        List<Phone> phones = phoneRepository.findAll();
        phones.forEach(phone -> {
            try {
                Response<List<DeviceEntity>> response =  api.
                        getDevice("6eb5e44c42b36282e15859515e4a2403cb7d0bbc9aab5850",phone.getDeviceName(), phone.getBrand(), 1).execute();
                phone.setCapabilities(DeviceEntityToPhoneCapabilitiesDtoMapper
                        .map(response.body().stream().findAny().orElse(null)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return phones.stream().map(PhoneToPhoneDtoMapper::map).collect(Collectors.toList());
    }

    public void bookPhone(User user, Long phoneId) throws InconsistentPhoneBookingException,DeviceNotFoundException{
        Optional<Phone> phoneQuery = phoneRepository.findById(phoneId);
        if(phoneQuery.isPresent()){
            Phone phone = phoneQuery.get();
            if(!phone.isAvailable()){
                throw new InconsistentPhoneBookingException("this phone is already booked");
            }
            PhoneBooking phoneBooking = PhoneBooking.builder().phone(phone).user(user).booked(Instant.now()).
                    build();
            phoneBookingRepository.saveAndFlush(phoneBooking);
            phone.setAvailable(false);
            phoneRepository.saveAndFlush(phone);
        }
        else{
            throw new DeviceNotFoundException("Device not found");
        }
    }

    public void returnPhone(User user, Long phoneId) throws InconsistentPhoneBookingException,DeviceNotFoundException{
        Optional<Phone> phoneQuery = phoneRepository.findById(phoneId);
        if(phoneQuery.isPresent()){
            Phone phone = phoneQuery.get();
            if(phone.isAvailable()){
                throw new InconsistentPhoneBookingException("this phone has not been booked already booked");
            }
            Optional<PhoneBooking> currentPhoneBookingOptional = phone.getPhoneBookings().stream().max(Comparator.comparing(PhoneBooking::getId));
            if(currentPhoneBookingOptional.isPresent()){
                PhoneBooking phoneBooking = currentPhoneBookingOptional.get();
                if(phoneBooking.getUser().getId()!=user.getId()){
                    throw new InconsistentPhoneBookingException("This device is not being hold by the user that is trying to return it");
                }
                phoneBooking.setReturned(Instant.now());
                phone.setAvailable(true);
                phoneRepository.saveAndFlush(phone);
                phoneBookingRepository.saveAndFlush(phoneBooking);

            }
            else{
                throw new InconsistentPhoneBookingException("This device booking is in an inconsistent state");
            }


        }
        else{
            throw new DeviceNotFoundException("Device not found");
        }
    }
}
