package by.tr.web.dao.impl.command.impl;

import by.tr.web.dao.exception.DAOException;
import by.tr.web.dao.impl.command.Command;
import by.tr.web.dao.parser.StudentsStAXParser;
import by.tr.web.domain.FilePath;
import by.tr.web.domain.Student;

import java.util.List;

public class ParseStAX implements Command {
    private StudentsStAXParser parser = new StudentsStAXParser();

    @Override
    public List<Student> execute() throws DAOException {

        parser.buildStudentList(FilePath.XML_FILE);
        return parser.getStudentList();
    }
}
