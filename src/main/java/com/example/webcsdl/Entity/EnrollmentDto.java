package com.example.webcsdl.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDto {
   private Long studentId;
   private String studentName;
   private Long courseId;
   private String courseName;
   private String enrollmentDate;
   private Double grade;
}
