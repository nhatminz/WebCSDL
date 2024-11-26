package com.example.webcsdl.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class StudentDto {
    public Long id;
    public String firstName;
    public String lastName;
    public String dateOfBirth;
    public String email;
    public String phoneNumber;
    public String address;
    public Long classId;
    public Long majorId;
    public BigDecimal gpa;
}
