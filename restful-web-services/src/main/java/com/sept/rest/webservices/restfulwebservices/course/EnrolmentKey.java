package com.sept.rest.webservices.restfulwebservices.course;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EnrolmentKey implements Serializable { // composite primary key for Enrolment class

    @Column(name = "student_id")
    Long userId;

    @Column(name = "course_id")
    Long courseId;

    public EnrolmentKey(){
    }
    
    public EnrolmentKey(Long userId, Long courseId) {
    	this.userId = userId;
    	this.courseId = courseId;
    }

}
