package com.example.enrollment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomId;

    @Column(unique = true, length = 20, nullable = false)
    private String roomCode;

    @Column(length = 50)
    private String buildingName;

    private Integer capacity;

    @Column(length = 20)
    private String roomType = "Lecture";

    private Boolean activeStatus = true;
}
