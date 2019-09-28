package com.sept.rest.webservice.restfulwebservice.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class studentsResource {
	
	 @Autowired
	    private studentHardcodeList studentsService;

	    @GetMapping("/Students")
	    public List<Students> getAllStudents() {
	        return studentsService.showAll();
	    }
	    
	    public Students serachStudent(@PathVariable Long id) {
	    	return studentsService.SearchStudentByID(id);
	    }

}