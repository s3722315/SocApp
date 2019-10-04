package com.sept.rest.webservice.restfulwebservice.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class StudentJpaResource {

	@Autowired
	private studentHardcodeList studentList;
	
	@Autowired
	private StudentJpaRepository studentJpaRepository;

    @GetMapping("/jpa/courses")
    public List<Students> getAllStudents() {
    	return studentJpaRepository.findAll();
    }

    @GetMapping("/jpa/courses/{id}")
    public Students getStudent(@PathVariable long id) {
    	return studentJpaRepository.findById(id).get();
    }
    
    
}
