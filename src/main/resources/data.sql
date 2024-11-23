-- Thêm dữ liệu vào bảng departments
INSERT INTO departments (id, department_name, location) VALUES
                                                            (1, 'Computer Science', 'Building A'),
                                                            (2, 'Electrical Engineering', 'Building B'),
                                                            (3, 'Mechanical Engineering', 'Building C');

-- Thêm dữ liệu vào bảng classes
INSERT INTO classes (id, class_name, class_description) VALUES
                                                            (1, 'CS1', 'Introduction to Computer Science'),
                                                            (2, 'CS2', 'Advanced Programming'),
                                                            (3, 'EE1', 'Basic Electrical Engineering');

-- Thêm dữ liệu vào bảng majors
INSERT INTO majors (id, major_name, description, department_id) VALUES
                                                                    (1, 'Computer Science', 'Focuses on software, algorithms, and systems.', 1),
                                                                    (2, 'Electrical Engineering', 'Covers circuits, electronics, and electromagnetics.', 2),
                                                                    (3, 'Mechanical Engineering', 'Focuses on mechanical systems and materials.', 3);

-- Thêm dữ liệu vào bảng teachers
INSERT INTO teachers (id, first_name, last_name, email, phone_number, department_id) VALUES
                                                                                         (1, 'Alice', 'Smith', 'alice.smith@example.com', '1234567890', 1),
                                                                                         (2, 'Bob', 'Johnson', 'bob.johnson@example.com', '0987654321', 2),
                                                                                         (3, 'Charlie', 'Brown', 'charlie.brown@example.com', '5678901234', 3);

-- Thêm dữ liệu vào bảng courses
INSERT INTO courses (id, course_name, course_code, credits, major_id, teacher_id) VALUES
                                                                                      (1, 'Introduction to Programming', 'CS101', 3, 1, 1),
                                                                                      (2, 'Data Structures', 'CS201', 4, 1, 1),
                                                                                      (3, 'Circuit Analysis', 'EE101', 3, 2, 2),
                                                                                      (4, 'Thermodynamics', 'ME101', 4, 3, 3);

-- Thêm dữ liệu vào bảng students
INSERT INTO students (id, first_name, last_name, date_of_birth, email, phone_number, address, class_id, major_id, gpa) VALUES
                                                                                                                           (1, 'John', 'Doe', '2000-01-01', 'john.doe@example.com', '1112223333', '123 Elm St', 1, 1, 3.5),
                                                                                                                           (2, 'Jane', 'Smith', '2001-02-02', 'jane.smith@example.com', '4445556666', '456 Maple Ave', 2, 1, 3.8),
                                                                                                                           (3, 'Sam', 'Green', '2002-03-03', 'sam.green@example.com', '7778889999', '789 Oak Ln', 3, 2, 3.2);

-- Thêm dữ liệu vào bảng classrooms
INSERT INTO classrooms (id, room_number, building, capacity) VALUES
                                                                 (1, '101', 'Building A', 50),
                                                                 (2, '202', 'Building B', 40),
                                                                 (3, '303', 'Building C', 30);

-- Thêm dữ liệu vào bảng scholarships
INSERT INTO scholarships (id, scholarship_name, amount, student_id) VALUES
                                                                        (1, 'Academic Excellence', 1000.00, 1),
                                                                        (2, 'Merit-Based', 800.00, 2),
                                                                        (3, 'Need-Based', 500.00, 3);

-- Thêm dữ liệu vào bảng enrollments
INSERT INTO enrollments (student_id, course_id, enrollment_date, grade) VALUES
                                                                            (1, 1, '2024-01-15', 3.7),
                                                                            (1, 2, '2024-01-15', 3.9),
                                                                            (2, 1, '2024-01-15', 3.5),
                                                                            (2, 3, '2024-01-15', 3.8),
                                                                            (3, 4, '2024-01-15', 3.2);

-- Thêm dữ liệu vào bảng course_schedule
INSERT INTO course_schedule (id, course_id, classroom_id, day_of_week, start_time, end_time) VALUES
                                                                                                 (1, 1, 1, 'Monday', '08:00:00', '10:00:00'),
                                                                                                 (2, 2, 1, 'Wednesday', '10:00:00', '12:00:00'),
                                                                                                 (3, 3, 2, 'Friday', '09:00:00', '11:00:00'),
                                                                                                 (4, 4, 3, 'Tuesday', '14:00:00', '16:00:00');
