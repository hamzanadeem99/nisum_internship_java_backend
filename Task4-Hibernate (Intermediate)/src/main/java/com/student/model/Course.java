package com.student.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseId")
    private int courseId;

    @Column(name = "courseName")
    private String courseName;

    @Column(name = "creditHours")
    private int creditHours;

    @ManyToOne
    @JoinColumn(name = "rollNo")
    private Student student;

    // No-arg constructor
    public Course() {}

    // Arg constructor
    public Course(String courseName, int creditHours) {
        this.courseName  = courseName;
        this.creditHours = creditHours;
    }

    // Getters
    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public Student getStudent() {
        return student;
    }

    // Setters
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName  = courseName;
    }

    public void setCreditHours (int creditHours) {
        this.creditHours = creditHours;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
