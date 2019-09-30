package com.sept.rest.webservice.restfulwebservice.student;

import java.net.URI;
import java.util.List;

import com.sept.rest.webservices.restfulwebservices.jwt.JwtUserDetails;
import com.sept.rest.webservices.restfulwebservices.student.Student;

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
public class StudentJpaResource {

	@Autowired
	private studentHardcodeList studentList;
	
	@Autowired
	private StudentJpaRepository studentJpaRepository;

    @GetMapping("/jpa/courses")
    public List<Student> getAllStudents() {
    	return studentJpaRepository.findAll();
    }

    @GetMapping("/jpa/courses/{id}")
    public Student getStudent(@PathVariable long id) {
    	return studentJpaRepository.findById(id).get();
    }
    
    
}
