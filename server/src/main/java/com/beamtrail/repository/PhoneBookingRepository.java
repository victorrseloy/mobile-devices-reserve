package com.beamtrail.repository;

import com.beamtrail.entity.PhoneBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneBookingRepository extends JpaRepository<PhoneBooking, Long> {
}
