package com.sept.rest.webservices.restfulwebservices.course;

import java.net.URI;
import java.util.List;

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
        return courseService.findAll();
    }

    @GetMapping("/jpa/courses/{id}")
    public Course getCourse(@PathVariable long id){
        return courseJpaRepository.findById(id).get();
    }

    @GetMapping("/jpa/courses/{id}/status")
    public Course.Status getCourseStatus(@PathVariable long id){
        return courseJpaRepository.findById(id).get().getStatus();
    }

    @GetMapping("/jpa/users/{username}/courses/{id}")       //user database not implemented yet
    public Course getCoursebyUser(@PathVariable String username, @PathVariable long id){
        return courseJpaRepository.findById(id).get();
    }

    @PutMapping("/jpa/courses/{id}/enroll")
    public ResponseEntity<Course> enrollCourse(@PathVariable long id, @RequestBody Course course){
        course.setStatus(Course.Status.enrolled);
        Course courseUpdated = courseJpaRepository.save(course);

        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }

    @PutMapping("/jpa/courses/{id}/drop")
    public ResponseEntity<Course> dropCourse(@PathVariable long id, @RequestBody Course course){
        course.setStatus(Course.Status.available);
        Course courseUpdated = courseJpaRepository.save(course);

        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }

}
