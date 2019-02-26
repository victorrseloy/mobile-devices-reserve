package com.mobilebook.repository;

import com.mobilebook.entity.PhoneBooking;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repository to fetch phone booking related data
 */
public interface PhoneBookingRepository extends JpaRepository<PhoneBooking, Long> {
}
