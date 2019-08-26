package com.sept.rest.webservices.restfulwebservices.course;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String code;
    private Status status;

    public enum Status {
        available, enrolled, full, unavailable
    }

    //constructor methods
    protected Course(){
    }

    public Course(long id, String name, String code, Status status){
        this.id = id;
        this.name = name;
        this.code = code;
        this.status = status;
    }

    //getter setter methods
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }
}
