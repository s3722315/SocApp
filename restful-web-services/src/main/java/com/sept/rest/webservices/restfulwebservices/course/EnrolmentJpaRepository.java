package com.sept.rest.webservices.restfulwebservices.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrolmentJpaRepository extends JpaRepository<Enrolment, Long> {
    List<Enrolment> findByStudentId(long id);
    List<Enrolment> findByCourseId(long id);
    Boolean existsByStudentIdAndCourseId(long studentId, long courseId);

}
