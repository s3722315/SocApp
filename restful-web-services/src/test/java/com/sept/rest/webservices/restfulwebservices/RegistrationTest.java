package com.sept.rest.webservices.restfulwebservices;

import com.sept.rest.webservices.restfulwebservices.course.CourseJpaRepository;
import com.sept.rest.webservices.restfulwebservices.course.CourseJpaResource;
import com.sept.rest.webservices.restfulwebservices.course.EnrolmentJpaRepository;
import com.sept.rest.webservices.restfulwebservices.jwt.StudentJpaRepository;
import com.sept.rest.webservices.restfulwebservices.jwt.resource.JwtAuthenticationRestController;
import com.sept.rest.webservices.restfulwebservices.jwt.resource.SignUpRequest;
import com.sept.rest.webservices.restfulwebservices.jwt.resource.SignUpResponse;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationTest {

    @Autowired
    CourseJpaRepository courseJpaRepository;

    @Autowired
    CourseJpaResource courseJpaResource;

    @Autowired
    StudentJpaRepository studentJpaRepository;

    @Autowired
    EnrolmentJpaRepository enrolmentJpaRepository;

    JwtAuthenticationRestController jwtAuthenticationRestController;

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
    public void registrationTest1(){ //test registration
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("testRegistration");
        signUpRequest.setPassword("dummy");
        jwtAuthenticationRestController.registerUser(signUpRequest);
        assertTrue(studentJpaRepository.existsByUsername("testRegitration"));
    }

    @Test
    public void registrationTest2(){ //test for duplicate registration
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("testRegistration");
        signUpRequest.setPassword("dummy");
        jwtAuthenticationRestController.registerUser(signUpRequest);
        assertEquals(new ResponseEntity<SignUpResponse>(new SignUpResponse(false, "Username is already taken!"),
                HttpStatus.BAD_REQUEST), jwtAuthenticationRestController.registerUser(signUpRequest));
    }

    @Test
    public void registrationTest3(){ //test for username exceeding character limit
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("testtesttesttesttesttesttesttesttesttestRegistration");
        signUpRequest.setPassword("dummy");
        assertEquals(new ResponseEntity<SignUpResponse>(new SignUpResponse(false, "Username is already taken!"),
                HttpStatus.BAD_REQUEST), jwtAuthenticationRestController.registerUser(signUpRequest));
    }

    @Test
    public void registrationTest4(){ //test for empty username String
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("");
        signUpRequest.setPassword("dummy");
        assertEquals(new ResponseEntity<SignUpResponse>(new SignUpResponse(false, "Username is already taken!"),
                HttpStatus.BAD_REQUEST), jwtAuthenticationRestController.registerUser(signUpRequest));
    }

    @Test
    public void registrationTest5(){ //test for empty password String
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("testRegistration");
        signUpRequest.setPassword("");
        assertEquals(new ResponseEntity<SignUpResponse>(new SignUpResponse(false, "Username is already taken!"),
                HttpStatus.BAD_REQUEST), jwtAuthenticationRestController.registerUser(signUpRequest));
    }

    @Test
    public void registrationTest6(){ //test validity of encrypted password
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("testRegistration");
        signUpRequest.setPassword("dummy");
        jwtAuthenticationRestController.registerUser(signUpRequest);
        assertEquals("$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e",
                studentJpaRepository.findByUsername("testRegistration").get().getPassword());
    }
}
