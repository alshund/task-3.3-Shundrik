package by.tr.web.dao;

import by.tr.web.domain.Student;

import java.util.List;

public interface PersonalAffairsDAO {
    List<Student> parseXML(String xmlParserType);
}
