package com.sept.rest.webservices.restfulwebservices.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CourseHardcodedService {

    private static List<Course> courses = new ArrayList<>();
    private static long idCounter = 0;

    static {
        courses.add(new Course(++idCounter, "sept", "Programming 1", "EEET0001", "available"));
        courses.add(new Course(++idCounter, "sept", "Web Programming", "EEET0002", "available"));
        courses.add(new Course(++idCounter, "sept", "Software Fundamentals", "COSC0034", "full"));
        courses.add(new Course(++idCounter, "sept", "Software Testing", "COSC0035", "unavailable"));
        courses.add(new Course(++idCounter, "sept", "Capstone Project", "EEET0003", "enrolled"));
    }

    public List<Course> findAll() { return courses; }

    public String checkStatus(Course course){
        return course.getStatus();
    }

    public Course findByID(long id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    public List<Course> findByEnrolled() {
        List<Course> enrolledCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getStatus().equals("enrolled")) {
                enrolledCourses.add(course);
            }
        }
        return enrolledCourses;
    }



}
