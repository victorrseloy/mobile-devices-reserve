package com.beamtrail.repository;


import com.beamtrail.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String login);
}