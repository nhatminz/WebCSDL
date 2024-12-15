package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.*;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfExportService {

    public byte[] exportStudentsToPdf(List<StudentDto> students) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(PageSize.A4.rotate());

            Document document = new Document(pdf);
            document.add(new Paragraph("Student List").setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER));
            Table table = new Table(new float[]{1, 2, 2, 3, 4, 3, 4, 2, 2});
            table.setWidth(UnitValue.createPercentValue(100));
            String[] headers = {"ID", "First Name", "Last Name", "Date of Birth", "Email", "Phone Number", "Address", "Class", "GPA"};
            for (String header : headers) {
                table.addHeaderCell(new Cell().add(new Paragraph(header).setBold().setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
            }
            for (StudentDto student : students) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(student.getId())).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(student.getFirstName()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(student.getLastName()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(student.getDateOfBirth()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(student.getEmail()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(student.getPhoneNumber()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(student.getAddress()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(student.getClassName()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(student.getGpa())).setFontSize(9)));
            }

            document.add(table);
            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error while generating PDF", e);
        }
    }

    public byte[] exportTeachersToPdf(List<TeacherDto> teachers) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(PageSize.A4.rotate());

            Document document = new Document(pdf);

            document.add(new Paragraph("Teacher List").setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER));
            Table table = new Table(new float[]{1, 2, 2, 4, 3, 3});
            table.setWidth(UnitValue.createPercentValue(100));
            String[] headers = {"ID", "First Name", "Last Name", "Email", "Phone Number", "Department"};
            for (String header : headers) {
                table.addHeaderCell(new Cell().add(new Paragraph(header).setBold().setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
            }
            for (TeacherDto teacher : teachers) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(teacher.getId())).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(teacher.getFirstName()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(teacher.getLastName()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(teacher.getEmail()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(teacher.getPhoneNumber()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(teacher.getDepartmentName()).setFontSize(9)));
            }

            document.add(table);
            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error while generating PDF for teachers", e);
        }
    }

    public byte[] exportSchoolClassesToPdf(List<SchoolClass> schoolClasses) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(PageSize.A4.rotate());

            Document document = new Document(pdf);

            document.add(new Paragraph("SchoolClass List").setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER));

            Table table = new Table(new float[]{1, 3, 4});
            table.setWidth(UnitValue.createPercentValue(100));

            String[] headers = {"ID", "Class Name", "Class Description"};
            for (String header : headers) {
                table.addHeaderCell(new Cell().add(new Paragraph(header).setBold().setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
            }

            for (SchoolClass schoolClass : schoolClasses) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(schoolClass.getId())).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(schoolClass.getClassName()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(schoolClass.getClassDescription()).setFontSize(9)));
            }

            document.add(table);
            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error while generating PDF for school classes", e);
        }
    }

    public byte[] exportCoursesToPdf(List<CourseDto> courses) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(PageSize.A4.rotate());

            Document document = new Document(pdf);
            document.add(new Paragraph("Course List").setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER));

            Table table = new Table(new float[]{1, 2, 2, 2, 2, 3});
            table.setWidth(UnitValue.createPercentValue(100));

            table.addHeaderCell(new Cell().add(new Paragraph("ID").setBold().setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
            table.addHeaderCell(new Cell().add(new Paragraph("Course Name").setBold().setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
            table.addHeaderCell(new Cell().add(new Paragraph("Course Code").setBold().setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
            table.addHeaderCell(new Cell().add(new Paragraph("Credits").setBold().setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
            table.addHeaderCell(new Cell().add(new Paragraph("Major").setBold().setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
            table.addHeaderCell(new Cell().add(new Paragraph("Teacher").setBold().setFontSize(10).setTextAlignment(TextAlignment.CENTER)));

            for (CourseDto course : courses) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(course.getId())).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(course.getCourseName()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(course.getCourseCode()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(course.getCredits())).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(course.getMajorName() != null ? course.getMajorName() : "").setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(course.getTeacherName() != null ? course.getTeacherName() : "").setFontSize(9)));
            }

            document.add(table);
            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error while generating PDF", e);
        }
    }

    public byte[] exportCourseSchedulesToPdf(List<CourseScheduleDto> courseSchedules) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(PageSize.A4.rotate());

            Document document = new Document(pdf);
            document.add(new Paragraph("Course Schedules").setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER));

            Table table = new Table(new float[]{1, 2, 3, 3, 4, 3});
            table.setWidth(UnitValue.createPercentValue(100));
            String[] headers = {"ID", "Day of Week", "Start Time", "End Time", "Course Name", "Classroom ID"};
            for (String header : headers) {
                table.addHeaderCell(new Cell().add(new Paragraph(header).setBold().setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
            }

            for (CourseScheduleDto schedule : courseSchedules) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(schedule.getId() != null ? schedule.getId() : "Chưa xác định")).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(schedule.getDayOfWeek() != null ? schedule.getDayOfWeek() : "Chưa xác định").setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(schedule.getStartTime() != null ? schedule.getStartTime() : "Chưa xác định").setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(schedule.getEndTime() != null ? schedule.getEndTime() : "Chưa xác định").setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(schedule.getCourseName() != null ? schedule.getCourseName() : "Chưa xác định").setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(schedule.getClassroomId() != null ? String.valueOf(schedule.getClassroomId()) : "Chưa xác định").setFontSize(9)));
            }

            document.add(table);
            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error while generating PDF", e);
        }
    }
    public byte[] exportDepartmentsToPdf(List<Department> departments) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(PageSize.A4.rotate());

            Document document = new Document(pdf);
            document.add(new Paragraph("Department List")
                    .setBold()
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER));

            Table table = new Table(new float[]{1, 3, 3});
            table.setWidth(UnitValue.createPercentValue(100));

            String[] headers = {"ID", "Department Name", "Location"};
            for (String header : headers) {
                table.addHeaderCell(new Cell().add(new Paragraph(header)
                        .setBold()
                        .setFontSize(10)
                        .setTextAlignment(TextAlignment.CENTER)));
            }

            for (Department department : departments) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(department.getId() != null ? department.getId() : "Chưa xác định")).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(department.getDepartmentName() != null ? department.getDepartmentName() : "Chưa xác định").setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(department.getLocation() != null ? department.getLocation() : "Chưa xác định").setFontSize(9)));
            }

            document.add(table);
            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error while generating PDF", e);
        }
    }
    public byte[] exportEnrollmentsToPdf(List<EnrollmentDto> enrollmentDtos) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(PageSize.A4.rotate());

            Document document = new Document(pdf);
            document.add(new Paragraph("Enrollment List")
                    .setBold()
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER));

            Table table = new Table(new float[]{1, 3, 1, 3, 2, 2});
            table.setWidth(UnitValue.createPercentValue(100));

            String[] headers = {"Student ID", "Student Name", "Course ID", "Course Name", "Enrollment Date", "Grade"};
            for (String header : headers) {
                table.addHeaderCell(new Cell().add(new Paragraph(header)
                        .setBold()
                        .setFontSize(10)
                        .setTextAlignment(TextAlignment.CENTER)));
            }

            for (EnrollmentDto dto : enrollmentDtos) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(dto.getStudentId())).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(dto.getStudentName()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(dto.getCourseId())).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(dto.getCourseName()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(dto.getEnrollmentDate() != null ? dto.getEnrollmentDate().toString() : "Chưa xác định").setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(dto.getGrade() != null ? dto.getGrade().toString() : "Chưa xác định").setFontSize(9)));
            }

            document.add(table);
            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error while generating PDF", e);
        }
    }
    public byte[] exportMajorsToPdf(List<MajorDto> majorDtos) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(PageSize.A4);

            Document document = new Document(pdf);
            document.add(new Paragraph("Majors List")
                    .setBold()
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER));

            Table table = new Table(new float[]{1, 3, 4, 3});
            table.setWidth(UnitValue.createPercentValue(100));

            String[] headers = {"ID", "Major Name", "Description", "Department Name"};
            for (String header : headers) {
                table.addHeaderCell(new Cell().add(new Paragraph(header).setBold().setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
            }

            for (MajorDto major : majorDtos) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(major.getId())).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(major.getMajorName()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(major.getDescription()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(major.getDepartmentName()).setFontSize(9)));
            }

            document.add(table);
            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error while generating PDF", e);
        }
    }
    public byte[] exportScholarshipsToPdf(List<ScholarshipDto> scholarshipDtos) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(PageSize.A4);

            Document document = new Document(pdf);
            document.add(new Paragraph("Scholarships List")
                    .setBold()
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER));

            Table table = new Table(new float[]{1, 3, 3, 3});
            table.setWidth(UnitValue.createPercentValue(100));

            String[] headers = {"ID", "Scholarship Name", "Amount", "Student Name"};
            for (String header : headers) {
                table.addHeaderCell(new Cell().add(new Paragraph(header).setBold().setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
            }

            for (ScholarshipDto scholarship : scholarshipDtos) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(scholarship.getId())).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(scholarship.getScholarshipName()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(scholarship.getAmount().toString()).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(scholarship.getStudentName()).setFontSize(9)));
            }

            document.add(table);
            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error while generating PDF", e);
        }
    }

}
