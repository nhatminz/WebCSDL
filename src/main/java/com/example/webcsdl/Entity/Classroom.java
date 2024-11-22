package com.example.webcsdl.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classrooms")
public class Classroom {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name = "room_number", nullable = false, length = 10)
    private String roomNumber;

    @Setter
    @Getter
    @Column(name = "building", length = 100)
    private String building;

    @Setter
    @Getter
    @Column(name = "capacity")
    private Integer capacity;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseSchedule> schedules = new ArrayList<>();

    // Getters and Setters

}
