package com.beamtrail.repository;


import com.beamtrail.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository to fetch Role related data
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);

}