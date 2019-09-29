package com.sept.rest.webservices.restfulwebservices.course;

import java.net.URI;
import java.util.List;

import com.sept.rest.webservices.restfulwebservices.jwt.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class CourseJpaResource {

    @Autowired
    private CourseHardcodedService courseService;

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @GetMapping("/jpa/courses")
    public List<Course> getAllCourses() {
        return courseJpaRepository.findAll();
    }

    @GetMapping("/jpa/courses/{id}")
    public Course getCourse(@PathVariable long id){
        return courseJpaRepository.findById(id).get();
    }

    @GetMapping("/jpa/courses/{id}/status")
    public String getCourseStatus(@PathVariable long id){

        return courseJpaRepository.findById(id).get().getStatus();
    }

    @GetMapping("/jpa/users/{username}/courses")
    public List<Course> getMyCourses(@PathVariable String username){
        //return courseJpaRepository.findByJwt_User_Details_Username(username);
        return courseJpaRepository.findByStatus("enrolled");
    }

    @PutMapping("/jpa/courses/{id}/enroll")
    public ResponseEntity<Course> enrollCourse(@PathVariable long id, @RequestBody Course course, @RequestBody JwtUserDetails user){
        course.setStatus("enrolled");
        Course courseUpdated = courseJpaRepository.save(course);

        return new ResponseEntity<Course>(course, HttpStatus.OK);

//        user.getCourses().add(course);
//        course.getStudents().add(user);
//
//        Course courseUpdated = courseJpaRepository.save(course);
//
//        return new ResponseEntity<Course>(course, HttpStatus.OK);


    }

    @PutMapping("/jpa/courses/{id}/drop")
    public ResponseEntity<Course> dropCourse(@PathVariable long id, @RequestBody Course course){
        course.setStatus("available");
        Course courseUpdated = courseJpaRepository.save(course);

        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }

}
