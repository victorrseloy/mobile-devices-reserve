package com.mobilebook.repository;

import com.mobilebook.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repository to fetch phone related data
 */
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
