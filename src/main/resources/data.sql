-- Bảng departments
INSERT INTO departments (department_name, location)
VALUES
    ('Computer Science', 'Building A'),
    ('Mathematics', 'Building B'),
    ('Physics', 'Building C');

-- Bảng classes
INSERT INTO classes (class_name, class_description)
VALUES
    ('CS1', 'Introduction to Computer Science'),
    ('CS2', 'Advanced Computer Science'),
    ('MATH1', 'Basic Mathematics');

-- Bảng majors
INSERT INTO majors (major_name, description, department_id)
VALUES
    ('Computer Science', 'Focus on software development and algorithms', 1),
    ('Mathematics', 'Advanced topics in algebra and calculus', 2),
    ('Physics', 'Explore the laws of the universe', 3);

-- Bảng teachers
INSERT INTO teachers (first_name, last_name, email, phone_number, department_id)
VALUES
    ('Alice', 'Johnson', 'alice.johnson@example.com', '123456789', 1),
    ('Bob', 'Smith', 'bob.smith@example.com', '987654321', 2),
    ('Charlie', 'Brown', 'charlie.brown@example.com', '567890123', 3);

-- Bảng courses
INSERT INTO courses (course_name, course_code, credits, major_id, teacher_id)
VALUES
    ('Programming 101', 'CS101', 4, 1, 1),
    ('Data Structures', 'CS201', 3, 1, 1),
    ('Calculus', 'MATH101', 4, 2, 2),
    ('Quantum Mechanics', 'PHYS101', 3, 3, 3);

-- Bảng students
INSERT INTO students (first_name, last_name, date_of_birth, email, phone_number, address, class_id, major_id, gpa)
VALUES
    ('John', 'Doe', '2001-05-15', 'john.doe@example.com', '123456789', '123 Main St', 1, 1, 3.8),
    ('Jane', 'Smith', '2000-12-10', 'jane.smith@example.com', '987654321', '456 Elm St', 2, 1, 3.9),
    ('Mark', 'Lee', '2002-07-22', 'mark.lee@example.com', '567890123', '789 Pine St', 3, 2, 3.5);

-- Bảng classrooms
INSERT INTO classrooms (room_number, building, capacity)
VALUES
    ('A101', 'Building A', 50),
    ('B201', 'Building B', 30),
    ('C301', 'Building C', 40);

-- Bảng scholarships
INSERT INTO scholarships (scholarship_name, amount, student_id)
VALUES
    ('Excellence Scholarship', 1000.00, 1),
    ('Merit Scholarship', 750.00, 2);

-- Bảng enrollments
INSERT INTO enrollments (student_id, course_id, enrollment_date, grade)
VALUES
    (1, 1, '2024-01-15', 3.7),
    (1, 2, '2024-01-15', 3.8),
    (2, 3, '2024-01-20', 3.9),
    (3, 4, '2024-02-01', 3.5);

-- Bảng course_schedule
INSERT INTO course_schedule (course_id, classroom_id, day_of_week, start_time, end_time)
VALUES
    (1, 1, 'Monday', '08:00:00', '10:00:00'),
    (2, 2, 'Wednesday', '10:00:00', '12:00:00'),
    (3, 3, 'Friday', '14:00:00', '16:00:00');
