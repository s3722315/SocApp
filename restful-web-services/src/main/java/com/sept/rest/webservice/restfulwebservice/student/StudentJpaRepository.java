package com.sept.rest.webservice.restfulwebservice.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sept.rest.webservices.restfulwebservices.student.Student;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student, Long>{
	List<Student> findByID(String id);
	

}
