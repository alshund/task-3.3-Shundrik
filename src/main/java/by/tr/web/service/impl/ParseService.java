package by.tr.web.service.impl;

import by.tr.web.dao.DAOFactory;
import by.tr.web.dao.PersonalAffairsDAO;
import by.tr.web.dao.exception.DAOException;
import by.tr.web.domain.ExceptionMessages;
import by.tr.web.domain.Student;
import by.tr.web.service.PersonalAffairsService;
import by.tr.web.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class ParseService implements PersonalAffairsService {
    private PersonalAffairsDAO personalAffairsDAO;

    public ParseService() {

        DAOFactory instance = DAOFactory.getInstance();
        personalAffairsDAO = instance.getXMLPersonalAffairsDAO();
    }

    @Override
    public List<Student> getStudentList(String xmlParserType, int currentPage, int recordsNumber) throws ServiceException {

        try {
            personalAffairsDAO.parseXML(xmlParserType);
            return getPage(currentPage, recordsNumber);
        } catch (DAOException e) {
            throw new ServiceException(ExceptionMessages.PARSE_XML, e);
        }
    }

    public List<Student> getPage(int currentPage, int recordsNumber) {

        List<Student> studentList = personalAffairsDAO.getStudentList();
        List<Student> page = new ArrayList<>();
        int lastRecord = currentPage * recordsNumber + recordsNumber;
        for (int recordIndex = currentPage * recordsNumber ; recordIndex < lastRecord; recordIndex++) {
            if (recordIndex < personalAffairsDAO.getStudentList().size()) {
                page.add(studentList.get(recordIndex));
            }
        }
        return page;
    }

    @Override
    public boolean isPageValuable(int newPage, int recordsNumber) {

        int firstRecord = newPage * recordsNumber;
        int lastRecord = firstRecord + recordsNumber;
        if (0 <= firstRecord && lastRecord - personalAffairsDAO.getStudentList().size() <= recordsNumber) {
            return true;
        }
        return false;
    }
}
