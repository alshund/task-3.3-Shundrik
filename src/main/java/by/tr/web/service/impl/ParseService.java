package by.tr.web.service.impl;

import by.tr.web.dao.DAOFactory;
import by.tr.web.dao.PersonalAffairsDAO;
import by.tr.web.domain.Student;
import by.tr.web.service.PersonalAffairsService;

import java.util.List;

public class ParseService implements PersonalAffairsService {
    private PersonalAffairsDAO personalAffairsDAO;

    public ParseService() {

        DAOFactory instance = DAOFactory.getInstance();
        personalAffairsDAO = instance.getXMLPersonalAffairsDAO();
    }

    @Override
    public List<Student> getStudentList(String xmlParserType) {

        return personalAffairsDAO.parseXML(xmlParserType);
    }
}
