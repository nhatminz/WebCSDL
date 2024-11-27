package com.example.webcsdl.Entity;
import  lombok.AllArgsConstructor;
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
public class CourseScheduleDto {
    public Long id;
    public String dayOfWeek;
    public String startTime;
    public String endTime;
    public Long courseId;
    public Long classroomId;
}

