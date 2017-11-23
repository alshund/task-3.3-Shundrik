package by.tr.web.service;

import by.tr.web.domain.Student;

import java.util.List;

public interface PersonalAffairsService {
    List<Student> getStudentList(String xmlParserType);
}
