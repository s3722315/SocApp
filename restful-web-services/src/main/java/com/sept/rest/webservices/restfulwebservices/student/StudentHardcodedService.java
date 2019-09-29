package com.sept.rest.webservices.restfulwebservices.student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentHardcodedService {

    private static List<Student> students = new ArrayList<>();
    private static long idCounter = 0;

    static {
        students.add(new Student(++idCounter, "Ando"));
        students.add(new Student(++idCounter, "Andy"));
        students.add(new Student(++idCounter, "Alvin"));
        students.add(new Student(++idCounter, "Hassan"));
    }

    public List<Student> findAll() {
    	return students;
    }

    public Student findByID(long id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}
