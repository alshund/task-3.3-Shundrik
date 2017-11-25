package by.tr.web.service;

import by.tr.web.domain.Student;
import by.tr.web.service.exception.ServiceException;

import java.util.List;

public interface PersonalAffairsService {
    List<Student> getStudentList(String xmlParserType, int pageNumber, int recordsNumber) throws ServiceException;
    List<Student> getPage(int currentPage, int recordsNumber);
    boolean isPageValuable(int newPage, int recordsNumber);
}
