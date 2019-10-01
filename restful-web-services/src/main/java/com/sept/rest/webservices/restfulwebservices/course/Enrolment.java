package com.sept.rest.webservices.restfulwebservices.course;

import com.sept.rest.webservices.restfulwebservices.jwt.JwtUserDetails;

import javax.persistence.*;

@Entity
public class Enrolment {

    @EmbeddedId
    EnrolmentKey id;

    @ManyToOne
    @MapsId("jwt_user_details_id")
    @JoinColumn(name = "jwt_user_details_id")
    JwtUserDetails jwtUserDetails;

    @ManyToOne
    @MapsId("course_id")
    @JoinColumn(name = "course_id")
    Course course;

    protected Enrolment(){
    }

    public EnrolmentKey getId() {
        return id;
    }

    public void setId(EnrolmentKey id) {
        this.id = id;
    }

    public JwtUserDetails getJwtUserDetails() {
        return jwtUserDetails;
    }

    public void setJwtUserDetails(JwtUserDetails jwtUserDetails) {
        this.jwtUserDetails = jwtUserDetails;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
