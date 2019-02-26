package com.beamtrail.service;


import com.beamtrail.entity.Role;
import com.beamtrail.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that allows to perform role related operations
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * find a role by it's name
     * @param roleStr
     * @return the role with the given name
     */
    public Role findByRoleName(String roleStr) {
        return roleRepository.findByRole(roleStr);
    }

    /**
     * Save a role to the DB
     * @param role
     * @return the saved role
     */
    public Role saveRole(String role) {
        Role roleObj = new Role(role);
        roleRepository.save(roleObj);
        return roleObj;
    }
}