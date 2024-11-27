package com.example.webcsdl.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class CourseDto {
    public Long id;
    public String courseName;
    public String courseCode;
    public Integer credits;
    public Long majorId;
    public Long teacherId;
}
