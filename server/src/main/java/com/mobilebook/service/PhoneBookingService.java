package com.mobilebook.service;

import com.aafanasev.fonoapi.DeviceEntity;
import com.aafanasev.fonoapi.retrofit.FonoApiFactory;
import com.aafanasev.fonoapi.retrofit.FonoApiService;
import com.mobilebook.entity.Phone;
import com.mobilebook.entity.PhoneBooking;
import com.mobilebook.entity.User;
import com.mobilebook.exception.DeviceNotFoundException;
import com.mobilebook.exception.InconsistentPhoneBookingException;
import com.mobilebook.mapper.DeviceEntityToPhoneCapabilitiesDtoMapper;
import com.mobilebook.mapper.PhoneToPhoneDtoMapper;
import com.mobilebook.pojo.PhoneDto;
import com.mobilebook.repository.PhoneBookingRepository;
import com.mobilebook.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A service that holds business the business logic related to devices booking and returns
 */
@Service
public class PhoneBookingService {

    public static final String FONO_API_KEY = "6eb5e44c42b36282e15859515e4a2403cb7d0bbc9aab5850";

    FonoApiService api = new FonoApiFactory().create();
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private PhoneBookingRepository phoneBookingRepository;


    /**
     * this method returns a list with all available devices
     *
     * @return a list containing all the devices present in the system
     */
    public List<PhoneDto> getPhones() {
        List<Phone> phones = phoneRepository.findAll();

        phones.forEach(phone -> {
            try {

                Response<List<DeviceEntity>> response =  api.
                        getDevice(FONO_API_KEY,phone.getDeviceName(), phone.getBrand(), 1).execute();

                phone.setCapabilities(DeviceEntityToPhoneCapabilitiesDtoMapper
                        .map(response.body().stream().findAny().orElse(null)));

            } catch (Exception e) {

                e.printStackTrace();

            }
        });

        return phones.stream().map(PhoneToPhoneDtoMapper::map).collect(Collectors.toList());
    }

    /**
     * Book a phone for a given user, this method handle inconsistent states like non existing devices
     * or trying to book already booked devices and raise the appropriate exception
     *
     * @param user - the user booking the phone
     * @param phoneId - the id of the phone being booked
     * @throws InconsistentPhoneBookingException - If the device has already been booked an
     * InconsistentPhoneBookingException will be thrown
     * @throws DeviceNotFoundException  - If the device being booked does not exist a DeviceNotFoundException
     * will be thrown
     */
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

    /**
     * return a booked phone and make it available, this method handle inconsistent states like non existing devices
     * or trying to return an already returned devices and raise the appropriate exception
     *
     * @param user - the user returng the phone
     * @param phoneId - the id of the phone being returned
     * @throws InconsistentPhoneBookingException - If the device has already been returned or is being returned
     * by someone that is not the current holder an  InconsistentPhoneBookingException will be thrown
     * @throws DeviceNotFoundException  - If the device being returned does not exist a DeviceNotFoundException
     * will be thrown
     */
    public void returnPhone(User user, Long phoneId) throws InconsistentPhoneBookingException,DeviceNotFoundException{

        Optional<Phone> phoneQuery = phoneRepository.findById(phoneId);
        if(phoneQuery.isPresent()){

            Phone phone = phoneQuery.get();

            if(phone.isAvailable()){
                throw new InconsistentPhoneBookingException("this phone has not been booked already booked");
            }

            Optional<PhoneBooking> currentPhoneBookingOptional = phone.getPhoneBookings()
                    .stream().max(Comparator.comparing(PhoneBooking::getId));

            if(currentPhoneBookingOptional.isPresent()){

                PhoneBooking phoneBooking = currentPhoneBookingOptional.get();

                if(phoneBooking.getUser().getId()!=user.getId()){
                    throw new InconsistentPhoneBookingException("This device is not being hold by the user that is " +
                            "trying to return it");
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
