package com.sept.rest.webservices.restfulwebservices.course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseJpaRepository extends JpaRepository<Course, Long> {
    List<Course> findByUsername(String username);
    List<Course> findByStatus(String status);

//    @Query("select x from Course c, JwtUserDetails u where c.id = u.id")
//    List<Course> findByJwtuserdetails_Username(String username);
}
