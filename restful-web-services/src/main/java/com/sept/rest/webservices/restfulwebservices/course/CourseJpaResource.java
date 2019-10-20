	package com.sept.rest.webservices.restfulwebservices.course;

import java.net.URI;
import java.util.ArrayList;
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
public class CourseJpaResource { // main class for course backend methods

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Autowired
    private EnrolmentJpaRepository enrolmentJpaRepository;

    @Autowired
    private StudentJpaRepository studentJpaRepository;


    @GetMapping("/jpa/{name}/courses")
    public List<Course> getAllCourses(@PathVariable String name) { // return a list of all courses to the frontend
        long studentId = studentJpaRepository.findByUsername(name).get().getId();
    	List<Course> list = courseJpaRepository.findAll();
    	int length = list.size();
    	for (int i = 0; i < length; i++) {
    		if (enrolmentJpaRepository.existsByStudentIdAndCourseId(studentId, i + 1)) {
    			list.get(i).setStatus("enrolled");
    		}
    	}
    	return list;
    }

    @GetMapping("/jpa/courses/{id}")
    public Course getCourse(@PathVariable long id){ // return a single Course object for particular usage
        return courseJpaRepository.findById(id).get();
    }

    @GetMapping("/jpa/users/{name}/{id}/enroll/status")
    public boolean getEnrolledStatus(@PathVariable String name, @PathVariable long id){
    	// check many-to-many table for existing enrolment relationship between Student and Course
        long studentId = studentJpaRepository.findByUsername(name).get().getId();
        return enrolmentJpaRepository.existsByStudentIdAndCourseId(studentId, id);
    }

    @GetMapping("/jpa/courses/{id}/status")
    public String getCourseStatus(@PathVariable long studentId, @PathVariable long courseId){
    	// check if course is available for enrollment or not
        if (courseJpaRepository.findById(courseId).get().getStatus().equals("available")){
            if (enrolmentJpaRepository.existsByStudentIdAndCourseId(studentId, courseId))
                return "enrolled";
            else return "available";
        }
        else return courseJpaRepository.findById(courseId).get().getStatus();
    }

    @GetMapping("/jpa/users/{username}/courses")
    public List<Course> getMyCourses(@PathVariable String username){ // return a list of course the particular student is enrolled in
    	long studentId = studentJpaRepository.findByUsername(username).get().getId();
    	List<Course> list = courseJpaRepository.findAll();
    	List<Course> myList = new ArrayList<>();
    	int length = list.size();
    	for (int i = 0; i < length; i++) {
    		if (enrolmentJpaRepository.existsByStudentIdAndCourseId(studentId, i + 1)) {
    			list.get(i).setStatus("enrolled");
    			myList.add(list.get(i));
    		}
    	}
    	
        return myList;
    }


    @PutMapping("/jpa/users/{username}/courses/{courseId}/enroll")
    public ResponseEntity<Course> enrollCourse(@PathVariable long courseId, @PathVariable String username){
    	// enroll a student in a course
    	
    	long studentId = studentJpaRepository.findByUsername(username).get().getId();
        Course course = courseJpaRepository.findById(courseId).get();
        Student student = studentJpaRepository.findById(studentId).get();

        if(course.getStatus().equals("unavailable")){
        	return new ResponseEntity<Course>(course, HttpStatus.OK);
        }
        if (course.getStatus().equals("full")){
        	return new ResponseEntity<Course>(course, HttpStatus.OK);
        }

        EnrolmentKey key = new EnrolmentKey(studentId, courseId);
        Enrolment enrolment = new Enrolment(key, student, course);
        Enrolment createdEnrolment = enrolmentJpaRepository.save(enrolment);

        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }

    @PutMapping("/jpa/users/{username}/courses/{courseId}/drop")
    public ResponseEntity<Course> dropCourse(@PathVariable long courseId, @PathVariable String username){
    	// drop a student from a previously enrolled course
    	
    	long studentId = studentJpaRepository.findByUsername(username).get().getId();
    	Course course = courseJpaRepository.findById(courseId).get();
        Student student = studentJpaRepository.findById(studentId).get();
    	if(course.getStatus().equals("unavailable")){
            return new ResponseEntity<Course>(course, HttpStatus.OK);
        }
        if (course.getStatus().equals("full")){
            return new ResponseEntity<Course>(course, HttpStatus.OK);
        }

        EnrolmentKey key = new EnrolmentKey(studentId, courseId);
        Enrolment enrolment = new Enrolment(key, student, course);
        enrolmentJpaRepository.delete(enrolment);

        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }

}
