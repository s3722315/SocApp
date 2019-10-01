package com.sept.rest.webservices.restfulwebservices.course;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sept.rest.webservices.restfulwebservices.jwt.Student;
import com.sept.rest.webservices.restfulwebservices.jwt.StudentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class CourseJpaResource {

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Autowired
    private EnrolmentJpaRepository enrolmentJpaRepository;

    @Autowired
    private StudentJpaRepository studentJpaRepository;

    @GetMapping("/jpa/courses")
    public List<Course> getAllCourses() {
        return courseJpaRepository.findAll();
    }

    @GetMapping("/jpa/courses/{id}")
    public Course getCourse(@PathVariable long id){
        return courseJpaRepository.findById(id).get();
    }

    @GetMapping("/jpa/users/{name}/{id}/enroll/status")
    public boolean getEnrolledStatus(@PathVariable String name, @PathVariable long id){
        long studentId = studentJpaRepository.findByUsername(name).get().getId();
        return enrolmentJpaRepository.existsByStudentIdAndCourseId(studentId, id);
    }

//    @GetMapping("/jpa/courses/{id}/status")
//    public String getCourseStatus(@PathVariable long id){
//        return courseJpaRepository.findById(id).get().getStatus();
//    }

    @GetMapping("/jpa/courses/{id}/status")
    public String getCourseStatus(@PathVariable long studentId, @PathVariable long courseId){
        if (courseJpaRepository.findById(courseId).get().getStatus().equals("available")){
            if (enrolmentJpaRepository.existsByStudentIdAndCourseId(studentId, courseId))
                return "enrolled";
            else return "available";
        }
        else return courseJpaRepository.findById(courseId).get().getStatus();
    }

    @GetMapping("/jpa/users/{username}/courses")
    public List<Course> getMyCourses(@PathVariable String username){
        return courseJpaRepository.findByStatus("enrolled");
    }

//    @GetMapping("/jpa/users/{username}/courses")
//    public List<Course> getMyCourses(@PathVariable long studentId, @PathVariable long courseId){
//        List<Enrolment> enrolledCourses = enrolmentJpaRepository.findByStudentId(studentId);
//
//        Set set = new HashSet<>(enrolledCourses);
//
//        return courseJpaRepository.findByEnrolments(set);
//    }

    @PutMapping("/jpa/courses/{id}/enroll")
    public ResponseEntity<Course> enrollCourse(@PathVariable long id, @RequestBody Course course){

        if(course.getStatus().equals("unavailable")){
            return new ResponseEntity<Course>(course, HttpStatus.OK);
        }
        if (course.getStatus().equals("full")){
            return new ResponseEntity<Course>(course, HttpStatus.OK);
        }
        course.setStatus("enrolled");
        Course courseUpdated = courseJpaRepository.save(course);

        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }

//    @PostMapping("/jpa/courses/{courseId}/enroll")
//    public void enrollCourse(@PathVariable long studentId, @PathVariable long courseId){
//
//        Course course = courseJpaRepository.findById(courseId).get();
//        Student student = studentJpaRepository.findById(studentId).get();
//
//        if(course.getStatus().equals("unavailable")){
//            return;
//        }
//        if (course.getStatus().equals("full")){
//            return;
//        }
//
//        Enrolment enrolment = new Enrolment(student, course);
//        Enrolment createdEnrolment = enrolmentJpaRepository.save(enrolment);
//
//        return;
//    }

    @PutMapping("/jpa/courses/{id}/drop")
    public ResponseEntity<Course> dropCourse(@PathVariable long id, @RequestBody Course course){
        if(course.getStatus().equals("unavailable")){
            return new ResponseEntity<Course>(course, HttpStatus.OK);
        }
        if (course.getStatus().equals("full")){
            return new ResponseEntity<Course>(course, HttpStatus.OK);
        }

        course.setStatus("available");
        Course courseUpdated = courseJpaRepository.save(course);

        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }

}
