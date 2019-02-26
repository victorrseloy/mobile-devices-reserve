package com.mobilebook.service;


import com.mobilebook.entity.Role;
import com.mobilebook.entity.User;
import com.mobilebook.exception.DuplicatedUserException;
import com.mobilebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Service that handles business logic for user related operations
 */
@Service
public class UserService {

    private static String ROLE_USER = "ROLE_USER";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    /**
     * Create a user with a given email and password
     * @param email
     * @param password
     * @return the newly created user
     * @throws DuplicatedUserException - if a user with the given email already exists this exception will be thrown
     */
    public User createUser(String email, String password) throws DuplicatedUserException {

        User user = userRepository.findByEmail(email);

        if (user != null) {
            throw new DuplicatedUserException("this user already exists");
        }

        user = new User(email, hashPassword(password), new ArrayList<>(), true);
        addRoleToUser(user, ROLE_USER);
        userRepository.save(user);
        return user;
    }

    /**
     * assing new roles to the user
     *
     * @param user
     * @param roleStr - role name
     */
    private void addRoleToUser(User user, String roleStr) {
        Role role = roleService.findByRoleName(roleStr);
        if (role == null) {
            role = roleService.saveRole(roleStr);
        }
        user.getRoles().add(role);
    }

    /**
     * hash the password using the spring configured password encoder
     * @param password - password to be encoded
     * @return encoded password
     */
    private String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * Fetchs the current user, normaly this user will be associated with a oauth token. So we can get it through
     * the http filter chain
     * @return the current user
     */
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        User user = null;
        if (currentUserName != null) {
            user = findByEmail(currentUserName);
        }
        return user;
    }

    /**
     * Find a user with a given email
     * @param email
     * @return the user
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}