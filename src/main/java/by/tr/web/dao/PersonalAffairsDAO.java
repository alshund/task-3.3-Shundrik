package by.tr.web.dao;

import by.tr.web.dao.exception.DAOException;
import by.tr.web.domain.Student;

import java.util.List;

public interface PersonalAffairsDAO {
    void parseXML(String xmlParserType) throws DAOException;
    List<Student> getStudentList();
}
