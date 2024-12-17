-- Schema for Departments
CREATE TABLE IF NOT EXISTS departments (
                             id BIGINT PRIMARY KEY,
                             department_name VARCHAR(100) NOT NULL,
                             location VARCHAR(100)
);

-- Schema for Classes
CREATE TABLE IF NOT EXISTS classes (
                                       id BIGINT PRIMARY KEY,
                                       class_name VARCHAR(50) NOT NULL UNIQUE,
    class_description VARCHAR(255)
    );

-- Schema for Majors
CREATE TABLE IF NOT EXISTS majors (
                        id BIGINT PRIMARY KEY,
                        major_name VARCHAR(100) NOT NULL UNIQUE,
                        description TEXT,
                        department_id BIGINT NOT NULL,
                        FOREIGN KEY (department_id) REFERENCES departments(id)
);

-- Schema for Teachers
CREATE TABLE IF NOT EXISTS teachers (
                          id BIGINT PRIMARY KEY,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          email VARCHAR(100),
                          phone_number VARCHAR(20),
                          department_id BIGINT,
                          FOREIGN KEY (department_id) REFERENCES departments(id)
);


-- Schema for Courses
CREATE TABLE IF NOT EXISTS courses (
                                       id BIGINT PRIMARY KEY,
                                       course_name VARCHAR(100) NOT NULL,
    course_code VARCHAR(20) NOT NULL,
    credits INT,
    major_id BIGINT,
    teacher_id BIGINT,
    FOREIGN KEY (major_id) REFERENCES majors(id),
    FOREIGN KEY (teacher_id) REFERENCES teachers(id)
    );


-- Schema for Students
CREATE TABLE IF NOT EXISTS students (
                          id BIGINT PRIMARY KEY,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          date_of_birth DATE,
                          email VARCHAR(100),
                          phone_number VARCHAR(20),
                          address VARCHAR(255),
                          class_id BIGINT NOT NULL,
                          major_id BIGINT NOT NULL,
                          gpa DECIMAL(3, 2),
                          FOREIGN KEY (class_id) REFERENCES classes(id),
                          FOREIGN KEY (major_id) REFERENCES majors(id)
);

-- Schema for Classrooms
CREATE TABLE IF NOT EXISTS classrooms (
                                          id BIGINT PRIMARY KEY,
                                          room_number VARCHAR(10) NOT NULL,
    building VARCHAR(100),
    capacity INT
    );

-- Schema for Scholarships
CREATE TABLE IF NOT EXISTS scholarships (
                              id BIGINT PRIMARY KEY,
                              scholarship_name VARCHAR(100) NOT NULL,
                              amount DECIMAL(10, 2),
                              student_id BIGINT,
                              FOREIGN KEY (student_id) REFERENCES students(id)
);


-- Schema for Enrollments
CREATE TABLE IF NOT EXISTS enrollments (
                                           student_id BIGINT,
                                           course_id BIGINT,
                                           enrollment_date DATE,
                                           grade DOUBLE PRECISION,
                                           PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
    );

-- Schema for Course Schedules
CREATE TABLE IF NOT EXISTS course_schedule (
                                 id BIGINT PRIMARY KEY,
                                 course_id BIGINT,
                                 classroom_id BIGINT,
                                 day_of_week VARCHAR(20),
                                 start_time TIME,
                                 end_time TIME,
                                 FOREIGN KEY (course_id) REFERENCES courses(id),
                                 FOREIGN KEY (classroom_id) REFERENCES classrooms(id)
);




-- Schema for MyAppUser
CREATE TABLE IF NOT EXISTS my_app_user (
                             id BIGSERIAL PRIMARY KEY ,
                             username VARCHAR(255) NOT NULL,
                             password VARCHAR(255) NOT NULL,
                             email VARCHAR(255) NOT NULL
);
