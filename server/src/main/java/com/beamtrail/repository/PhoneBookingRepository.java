package com.beamtrail.repository;

import com.beamtrail.entity.PhoneBooking;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repository to fetch phone booking related data
 */
public interface PhoneBookingRepository extends JpaRepository<PhoneBooking, Long> {
}
