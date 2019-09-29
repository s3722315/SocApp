package com.sept.rest.webservice.restfulwebservice.student;

import java.util.ArrayList;
import java.util.List;

public class studentHardcodeList {

	private static List<Students> Students = new ArrayList<>();
    private static long idCounter = 0;

    static {
    	Students.add(new Students(++idCounter, "Nahid Hasan", "CSE"));
    	Students.add(new Students(++idCounter, "Mehedi Hasan", "BBA"));
    	Students.add(new Students(++idCounter, "Robert D. Junior", "IPE"));
    }

    public List<Students> showAll() { return Students; }


    public Students SearchStudentByID(long id) {
        for (Students S : Students) {
            if (S.getSid() == id) {
                return S;
            }
        }
        return null;
    }
}