package org.example.sonrise_parkolo.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "parking_spot")
@Data
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean occupied;
}
