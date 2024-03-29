package com.mobilebook.service;

import com.mobilebook.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Service required by the spring security to load users associated with Oauth Tokens
 */
@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.mobilebook.entity.User dbUser = userService.findByEmail(username);

        if (dbUser == null) {
            throw new UsernameNotFoundException(String.format("User '%s' can not be found", username));
        }

        User user = new User(dbUser.getEmail(), dbUser.getPassword(), dbUser.isActive(),
                true, true,
                true,
                loadAuthorities(dbUser));
        return user;
    }

    public Collection<GrantedAuthority> loadAuthorities(com.mobilebook.entity.User user) {
        Collection<Role> userAuthorities = user.getRoles();
        Collection<GrantedAuthority> authorities =
                userAuthorities.stream()
                        .map(userAuthority -> new SimpleGrantedAuthority(userAuthority.getRole()))
                        .collect(Collectors.toCollection(ArrayList::new));
        return authorities;
    }

}