package com.sept.rest.webservices.restfulwebservices.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);
}
