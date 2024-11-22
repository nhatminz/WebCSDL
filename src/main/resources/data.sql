

CREATE TABLE departments (
                             department_name VARCHAR(100),
                             location VARCHAR(100),
                             id INT AUTO_INCREMENT primary key
);

CREATE TABLE classes (
                         class_name VARCHAR(50),  -- Tên lớp (VD: CS1, CS2)
                         class_description VARCHAR(255),
                         id INT PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE majors (
                        major_name VARCHAR(100),  -- Tên ngành (VD: Computer Science)
                        description TEXT,
                        department_id int,
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        constraint fk_department foreign key (department_id) references departments(id)
);

-- tao bang teachers
CREATE TABLE teachers (
                          first_name VARCHAR(50),
                          last_name VARCHAR(50),
                          email VARCHAR(100),
                          phone_number VARCHAR(20),
                          department_id int,
                          CONSTRAINT fk_department2 FOREIGN KEY (department_id) REFERENCES departments(id),
                          id INT PRIMARY KEY AUTO_INCREMENT
);


-- tao bang courses
CREATE TABLE courses (
                         course_name VARCHAR(100),
                         course_code VARCHAR(20),  -- Mã lớp học (VD: CS1, CS2)
                         credits INT,
                         major_id INT,  -- Khóa học thuộc ngành nào
                         teacher_id INT,
                         CONSTRAINT fk_major_course FOREIGN KEY (major_id) REFERENCES majors(id),
                         CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES teachers(id),
                         id INT PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE students (
                          first_name VARCHAR(50),
                          last_name VARCHAR(50),
                          date_of_birth DATE,
                          email VARCHAR(100),
                          phone_number VARCHAR(20),
                          address VARCHAR(255),
                          class_id INT,  -- Liên kết với bảng class
                          major_id INT,  -- Liên kết với ngành học
                          gpa DECIMAL(3, 2),  -- Thang điểm 4
                          CONSTRAINT fk_class FOREIGN KEY (class_id) REFERENCES classes(id),
                          CONSTRAINT fk_major FOREIGN KEY (major_id) REFERENCES majors(id),
                          id INT PRIMARY KEY AUTO_INCREMENT
);







CREATE TABLE classrooms (
                            room_number VARCHAR(10),
                            building VARCHAR(100),
                            capacity INT,
                            id INT PRIMARY KEY AUTO_INCREMENT
);


-- tao bang scholarships
CREATE TABLE scholarships (
                              scholarship_name VARCHAR(100),
                              amount DECIMAL(10, 2),
                              student_id INT,
                              CONSTRAINT fk_scholarship_student FOREIGN KEY (student_id) REFERENCES students(id),
                              id INT PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE enrollments (
                             student_id INT,
                             course_id INT,
                             enrollment_date DATE,
                             grade DECIMAL(3, 2),  -- Điểm số cho khóa học (thang điểm 4)
                             PRIMARY KEY (student_id, course_id),
                             CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES students(id),
                             CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses(id)
);

-- tao bang course_schedule
CREATE TABLE course_schedule (
                                 course_id INT,
                                 classroom_id INT,
                                 day_of_week VARCHAR(20),
                                 start_time TIME,
                                 end_time TIME,
                                 CONSTRAINT fk_schedule_course FOREIGN KEY (course_id) REFERENCES courses(id),
                                 CONSTRAINT fk_schedule_classroom FOREIGN KEY (classroom_id) REFERENCES classrooms(id),
                                 id INT PRIMARY KEY AUTO_INCREMENT
);

