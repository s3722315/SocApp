package com.sept.rest.webservice.restfulwebservice.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sept.rest.webservice.restfulwebservice.student.Students;

@Repository
public interface StudentJpaRepository extends JpaRepository<Students, Long>{
	List<Students> findByID(String id);
	long count();
	

}
