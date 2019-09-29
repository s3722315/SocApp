package com.sept.rest.webservices.restfulwebservices.course;


import com.sept.rest.webservices.restfulwebservices.jwt.JwtUserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String coursename;
    private String code;
    private String status;

    @ManyToMany
    Set<JwtUserDetails> enrolledStudents;

    //constructor methods
    protected Course(){
    }

    public Course(long id, String username, String coursename, String code, String status){
        this.id = id;
        this.username = username;
        this.coursename = coursename;
        this.code = code;
        this.status = status;
    }

    //getter setter methods
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getCoursename() { return coursename; }

    public void setCoursename(String coursename) { this.coursename = coursename; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public Set<JwtUserDetails> getStudents() { return enrolledStudents; }
}
