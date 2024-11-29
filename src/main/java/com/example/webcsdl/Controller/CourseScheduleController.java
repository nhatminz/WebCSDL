package com.example.webcsdl.Controller;

import com.example.webcsdl.Entity.*;
import com.example.webcsdl.Service.ClassroomServiceImpl;
import com.example.webcsdl.Service.CourseScheduleServiceImpl;
import com.example.webcsdl.Service.CourseScheduleServices;
import com.example.webcsdl.Service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CourseScheduleController {
    @Autowired
    private CourseScheduleServiceImpl courseScheduleServiceImpl;

    @Autowired
    private CourseServiceImpl courseServiceImpl;

    @Autowired
    private ClassroomServiceImpl classroomServiceImpl;

    @GetMapping("/CourseSchedule")
    public String showCourseScheduleManagement(Model model) {
        model.addAttribute("courseSchedules", courseScheduleServiceImpl.getAllCourseSchedule());
        model.addAttribute("courseSchedule", new CourseScheduleDto());
        model.addAttribute("courses", courseServiceImpl.getAllCourse());
        model.addAttribute("classrooms", classroomServiceImpl.getAllClassrooms());
        return "CourseSchedule";
    }

    @PostMapping("/CourseSchedules/add")
    public String addCourseSchedule(@ModelAttribute("courseSchedule") CourseScheduleDto courseScheduleDto) {
        courseScheduleServiceImpl.saveCourseSchedule(toEntity(courseScheduleDto));
        return "redirect:/CourseSchedule";
    }

    @GetMapping("/CourseSchedule/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        CourseSchedule courseSchedule = courseScheduleServiceImpl.getById(id);
        if (courseSchedule == null) {
            throw new RuntimeException("not found with ID: " + id);
        }
        CourseScheduleDto courseScheduleDto = toDto(courseSchedule);
        model.addAttribute("courseSchedule", courseSchedule);
        model.addAttribute("courseScheduleDto", courseScheduleDto);
        model.addAttribute("courses", courseServiceImpl.getAllCourse());
        model.addAttribute("classrooms", classroomServiceImpl.getAllClassrooms());
        return "updateCourseScheduleForm";
    }

    @PostMapping("/CourseSchedule/save")
    public String updateCourseSchedule(@ModelAttribute("courseScheduleDto") CourseScheduleDto courseScheduleDto) {
        CourseSchedule courseSchedule = toEntity(courseScheduleDto);
        courseScheduleServiceImpl.saveCourseSchedule(courseSchedule);
        return "redirect:/CourseSchedule";
    }

    @GetMapping("/CourseSchedule/all")
    public ResponseEntity<List<CourseScheduleDto>> getAllCourseSchedules() {
        List<CourseSchedule> schedules = courseScheduleServiceImpl.getAllCourseSchedule();
        List<CourseScheduleDto> scheduleDtos = schedules.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(scheduleDtos);
    }


    @GetMapping("/CourseSchedule/search")
    public ResponseEntity<List<CourseScheduleDto>> searchCourseSchedules(@RequestParam(required = false) String query) {
        List<CourseSchedule> schedules;
        if (query == null || query.isEmpty()) {
            schedules = courseScheduleServiceImpl.getAllCourseSchedule();
        } else {
            schedules = courseScheduleServiceImpl.searchSchedulesByCourseName(Long.valueOf(query));
        }
        List<CourseScheduleDto> scheduleDtos = schedules.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(scheduleDtos);
    }


    @GetMapping("/deleteCourseSchedule/{id}")
    public String deleteCourseScheduleById(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        try {
            courseScheduleServiceImpl.deleteViaId(id);
            redirectAttributes.addFlashAttribute("message", "Course Schedule deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete with ID: " + id);
        }
        return "redirect:/CourseSchedule";
    }

    private CourseScheduleDto toDto(CourseSchedule schedule) {
        CourseScheduleDto scheduleDto = new CourseScheduleDto();
        scheduleDto.setId(schedule.getId());
        scheduleDto.setDayOfWeek(schedule.getDayOfWeek());
        scheduleDto.setStartTime(String.valueOf(schedule.getStartTime()));
        scheduleDto.setEndTime(String.valueOf(schedule.getEndTime()));
        scheduleDto.setCourseId(schedule.getCourse().getId());
        scheduleDto.setClassroomId(schedule.getClassroom().getId());
        scheduleDto.setCourseName(schedule.getCourse().getCourseName());
        scheduleDto.setScheduleTime(schedule.getDayOfWeek() + " " + schedule.getStartTime() + " - " + schedule.getEndTime());
        return scheduleDto;
    }

    public CourseSchedule toEntity(CourseScheduleDto dto) {
        CourseSchedule courseSchedule = new CourseSchedule();
        courseSchedule.setId(dto.getId());
        courseSchedule.setStartTime(Time.valueOf(dto.getStartTime()));
        courseSchedule.setEndTime(Time.valueOf(dto.getEndTime()));
        courseSchedule.setDayOfWeek(dto.getDayOfWeek());
        Course course = courseServiceImpl.getById(dto.getCourseId());
        courseSchedule.setCourse(course);

        Classroom classroom = classroomServiceImpl.getById(dto.getClassroomId());
        courseSchedule.setClassroom(classroom);
        return courseSchedule;
    }
}
