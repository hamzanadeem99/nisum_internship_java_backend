package com.student.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rollNo")
    private int rollNo;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Course> courses = new ArrayList<>();

    // Constructors
    public Student() {
        // already initialized above (safe)
    }

    public Student(String name, int age, String email) {
        this.name  = name;
        this.age   = age;
        this.email = email;
    }

    // Helper Methods
    public void addCourse(Course course) {
        courses.add(course);
        course.setStudent(this);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.setStudent(null);
    }

    // Getters
    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    // Setters
    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourses(List<Course> courses) {
        // DO NOT replace list → Hibernate issue
        this.courses.clear();

        if (courses != null) {
            for (Course c : courses) {
                addCourse(c);
            }
        }
    }
}
