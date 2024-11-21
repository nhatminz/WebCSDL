package com.example.webcsdl.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "majors")
public class Majors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AI (Auto Increment)
    private Integer id;

    @Column(name = "major_name", length = 100, nullable = false)
    private String majorName;

    @Column(name = "description", columnDefinition = "TEXT", nullable = true)
    private String description;

    // Getters và Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
