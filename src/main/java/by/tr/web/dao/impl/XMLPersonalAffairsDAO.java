package by.tr.web.dao.impl;

import by.tr.web.dao.PersonalAffairsDAO;
import by.tr.web.dao.exception.DAOException;
import by.tr.web.dao.impl.command.Command;
import by.tr.web.dao.impl.command.CommandDirector;
import by.tr.web.domain.Student;

import java.util.List;

public class XMLPersonalAffairsDAO implements PersonalAffairsDAO {
    private CommandDirector commandDirector = new CommandDirector();
    private List<Student> studentList;

    @Override
    public void parseXML(String xmlParserType) throws DAOException {

        Command command = commandDirector.getCommand(xmlParserType);
        studentList = command.execute();
    }

    @Override
    public List<Student> getStudentList() {
        return studentList;
    }
}
