package com.sept.rest.webservices.restfulwebservices.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrolmentJpaRepository extends JpaRepository<Enrolment, Long> {
    List<Enrolment> findByStudentId(long id);
    List<Enrolment> findByCourseId(long id);

//    @Query("select x from Course c, Student u where c.id = u.id")
//    List<Course> findByJwtuserdetails_Username(String username);

    //jdbc:mysql://google/RMITSocAppsDB?cloudSqlInstance=rmitsocappsdb:australia-southeast1:myinstance
    //jdbc:mysql://${MYSQL_HOST:localhost}:3306/rmitsocappsdb
}
