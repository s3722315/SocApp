package com.sept.rest.webservices.restfulwebservices.jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUsername(String username);
    Boolean existsByUsername(String username);

}