package com.mobilebook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

/**
 * PhonBooking entity, holds all device booking related data. It makes possible to have a history of
 * who borrowed the devices, who currently have it and when it was returned
 */
@Entity
@Table(name = "phone_booking")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Phone phone;

    @Column(name = "booked")
    private Instant booked;

    @Column(name = "returned")
    private Instant returned;
}
