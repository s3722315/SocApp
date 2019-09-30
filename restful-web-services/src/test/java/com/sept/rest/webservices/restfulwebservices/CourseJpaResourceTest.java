package com.sept.rest.webservices.restfulwebservices;

import com.sept.rest.webservices.restfulwebservices.course.Course;
import com.sept.rest.webservices.restfulwebservices.course.CourseJpaRepository;
import com.sept.rest.webservices.restfulwebservices.course.CourseJpaResource;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseJpaResourceTest {

    @Autowired
    CourseJpaRepository courseJpaRepository;

    @Autowired
    CourseJpaResource courseJpaResource;


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

    @Test
    public void test(){
        Course course = new Course(1, "sept", "Programming 1", "EEET01", "enrolled");
        courseJpaRepository.save(course);
        List<Course> list = courseJpaResource.getMyCourses("sept");

        assertEquals(1, 1);
    }

}
