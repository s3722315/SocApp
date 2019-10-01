package com.sept.rest.webservices.restfulwebservices.course;

import com.sept.rest.webservices.restfulwebservices.jwt.Student;

import javax.persistence.*;

@Entity
public class Enrolment {

    @EmbeddedId
    EnrolmentKey id;

    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @MapsId("course_id")
    @JoinColumn(name = "course_id")
    Course course;

    protected Enrolment(){
    }

    public Enrolment(Student student, Course course){
        this.student = student;
        this.course = course;
    }

    public EnrolmentKey getId() { return id; }

    public void setId(EnrolmentKey id) { this.id = id; }

    public Student getStudent() { return student; }

    public void setStudent(Student student) { this.student = student; }

    public Course getCourse() { return course; }

    public void setCourse(Course course) { this.course = course; }
}
