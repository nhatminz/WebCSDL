package com.example.webcsdl.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseScheduleDto {
    private Long id;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private Long courseId;
    private Long classroomId;
    private String courseName;
    private String scheduleTime;
    private String teacherName;
}
