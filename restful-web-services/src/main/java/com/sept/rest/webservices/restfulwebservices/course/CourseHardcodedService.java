package com.sept.rest.webservices.restfulwebservices.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CourseHardcodedService {

    private static List<Course> courses = new ArrayList<>();
    private static long idCounter = 0;

    static {
        courses.add(new Course(++idCounter, "Programming 1", "EEET0001", Course.Status.available));
        courses.add(new Course(++idCounter, "Web Programming", "EEET0002", Course.Status.available));
        courses.add(new Course(++idCounter, "Software Fundamentals", "COSC0034", Course.Status.full));
        courses.add(new Course(++idCounter, "Software Testing", "COSC0045", Course.Status.unavailable));
    }

    public List<Course> findAll() { return courses; }

    public Course.Status checkStatus(Course course){
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



}
