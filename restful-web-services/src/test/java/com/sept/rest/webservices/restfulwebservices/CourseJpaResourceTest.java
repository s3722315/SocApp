package com.sept.rest.webservices.restfulwebservices;

import com.sept.rest.webservice.restfulwebservice.student.StudentJpaResource;
import com.sept.rest.webservices.restfulwebservices.course.*;
import com.sept.rest.webservices.restfulwebservices.jwt.Student;
import com.sept.rest.webservices.restfulwebservices.jwt.StudentJpaRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseJpaResourceTest {

    @Autowired
    CourseJpaRepository courseJpaRepository;

    @Autowired
    CourseJpaResource courseJpaResource;

    @Autowired
    StudentJpaRepository studentJpaRepository;

    @Autowired
    private EnrolmentJpaRepository enrolmentJpaRepository;

    @BeforeAll
    static void init() {

    }

    @AfterAll
    static void finish() {

    }

    @BeforeEach
    void setUp() {}

    @AfterEach
    void tearDown() {}

    //testing enrolment method
    @Test
    public void testEnroll1(){ //test if student can enroll in available course
        Student student = new Student(60L, "testDummy", "dummy");
        studentJpaRepository.save(student);
        courseJpaResource.enrollCourse(2, "testDummy");
        assertTrue(courseJpaResource.getEnrolledStatus("testDummy", 2));
        courseJpaResource.dropCourse(2, "testDummy");
        studentJpaRepository.delete(student);
    }

    @Test
    public void testEnroll2(){ //test scenario where student enrolls in unavailable course
    	Student student = new Student(60L, "testDummy", "dummy");
        studentJpaRepository.save(student);
        courseJpaResource.enrollCourse(4, "testDummy");
        assertFalse(courseJpaResource.getEnrolledStatus("testDummy", 4));
        studentJpaRepository.delete(student);
    }

    @Test
    public void testEnroll3(){ //test scenario where student enrolls is full course
    	Student student = new Student(60L, "testDummy", "dummy");
        studentJpaRepository.save(student);
        courseJpaResource.enrollCourse(3, "testDummy");
        assertFalse(courseJpaResource.getEnrolledStatus("testDummy", 3));
        studentJpaRepository.delete(student);
    }


    //test course withdrawal method
    @Test
    public void testDrop1(){ //test dropping a course
    	Student student = new Student(60L, "testDummy", "dummy");
        studentJpaRepository.save(student);
        EnrolmentKey key = new EnrolmentKey(student.getId(), 2L);
        Enrolment enrolment = new Enrolment(key, student, courseJpaResource.getCourse(2L));
        Enrolment createdEnrolment = enrolmentJpaRepository.save(enrolment);
        courseJpaResource.dropCourse(2, "testDummy");
        assertFalse(courseJpaResource.getEnrolledStatus("testDummy", 2));
        studentJpaRepository.delete(student);
    }

    @Test
    public void testDrop2(){ //test dropping an unavailable course
    	Student student = new Student(60L, "testDummy", "dummy");
        studentJpaRepository.save(student);
        courseJpaResource.dropCourse(4, "testDummy");
        assertFalse(courseJpaResource.getEnrolledStatus("testDummy", 4));
        studentJpaRepository.delete(student);
    }

    @Test
    public void testDrop3(){ //test dropping a full course
    	Student student = new Student(60L, "testDummy", "dummy");
        studentJpaRepository.save(student);
        courseJpaResource.dropCourse(3, "testDummy");
        assertFalse(courseJpaResource.getEnrolledStatus("testDummy", 3));
        studentJpaRepository.delete(student);
    }

}
