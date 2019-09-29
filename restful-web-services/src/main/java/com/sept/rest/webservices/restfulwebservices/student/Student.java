package com.sept.rest.webservices.restfulwebservices.student;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sept.rest.webservices.restfulwebservices.course.Course;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private List<Course> courseList = new ArrayList<>();


    protected Student(){
    }

    public Student(long id, String name){
        this.id = id;
        this.name = name;
    }

    public long getId() {
    	return this.id;
    }

    public void setId(long id) {
    	this.id = id;
    }

    public String getName() {
    	return this.name;
    }

    public void setName(String name) {
    	this.name = name;
    }
    
    public List<Course> listCourse() {
    	return this.courseList;
    }
    
}
