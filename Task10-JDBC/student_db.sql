-- Student Management Database

CREATE DATABASE IF NOT EXISTS student_db;
USE student_db;

-- Table 1: Student
CREATE TABLE IF NOT EXISTS Student (
    rollNo     INT            PRIMARY KEY,
    name       VARCHAR(100)   NOT NULL,
    age        INT            NOT NULL,
    email      VARCHAR(100)   NOT NULL
    );

-- Table 2: Course
CREATE TABLE IF NOT EXISTS Course (
    courseId    INT           PRIMARY KEY AUTO_INCREMENT,
    rollNo      INT           NOT NULL,
    courseName  VARCHAR(100)  NOT NULL,
    creditHours INT           NOT NULL,
    FOREIGN KEY (rollNo) REFERENCES Student(rollNo)
    );

-- Sample Data
INSERT INTO Student VALUES (1001, "Ali Akhtar",  25, "aliakhtar@gmail.com");
INSERT INTO Student VALUES (1002, "Hamza Khan",  27, "hamzakhan@gmail.com");
INSERT INTO Student VALUES (1003, "Umar Farooq", 31, "umarfarooq@gmail.com");

INSERT INTO Course (rollNo, courseName, creditHours) VALUES (1001, "Object Oriented Programming", 3);
INSERT INTO Course (rollNo, courseName, creditHours) VALUES (1001, "Database Systems",            3);
INSERT INTO Course (rollNo, courseName, creditHours) VALUES (1001, "Data Structures",             3);
INSERT INTO Course (rollNo, courseName, creditHours) VALUES (1002, "Web Design & Development",    3);
INSERT INTO Course (rollNo, courseName, creditHours) VALUES (1002, "Operating Systems",           3);
INSERT INTO Course (rollNo, courseName, creditHours) VALUES (1002, "Compiler Construction",       3);
INSERT INTO Course (rollNo, courseName, creditHours) VALUES (1003, "Machine Learning",            3);
INSERT INTO Course (rollNo, courseName, creditHours) VALUES (1003, "Computer Networks",           3);
INSERT INTO Course (rollNo, courseName, creditHours) VALUES (1003, "Software Engineering",        3);
