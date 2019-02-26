package com.mobilebook.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * User entity, holds user related information
 */
@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private boolean active;
    private Instant created;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "fk_user", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "fk_role",
                    nullable = false, updatable = false)})
    private List<Role> roles;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PhoneBooking> phoneBookings = new ArrayList<>();

    public User(String email, String password, List<Role> roles, boolean active) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.active = active;
        this.created = Instant.now();
    }

}