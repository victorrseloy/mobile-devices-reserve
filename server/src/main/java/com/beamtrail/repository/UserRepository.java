package com.beamtrail.repository;


import com.beamtrail.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * A repository to fetch User related data
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String login);
}