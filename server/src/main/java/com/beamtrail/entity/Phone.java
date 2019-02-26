package com.beamtrail.entity;

import com.beamtrail.pojo.PhoneCapabilitiesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Phone entity, holds phone related data
 */
@Entity
@Table(name = "phone")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "deviceName")
    private String deviceName;

    @Column(name = "brand")
    private String brand;

    @Column(name = "available")
    private boolean available;

    @OneToMany(
            mappedBy = "phone",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PhoneBooking> phoneBookings = new ArrayList<>();

    @Transient
    private PhoneCapabilitiesDto capabilities;
}
