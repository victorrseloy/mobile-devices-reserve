package com.beamtrail.repository;

import com.beamtrail.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repository to fetch phone related data
 */
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
