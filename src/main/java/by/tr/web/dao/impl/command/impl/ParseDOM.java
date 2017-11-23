package by.tr.web.dao.impl.command.impl;

import by.tr.web.dao.impl.command.Command;
import by.tr.web.dao.parser.StudentsDOMParser;
import by.tr.web.domain.Student;

import java.util.List;

public class ParseDOM implements Command {
    private StudentsDOMParser parser = new StudentsDOMParser();

    @Override
    public List<Student> execute() {

        parser.buildStudentList("/xml/Students.xml");
        return parser.getStudentList();
    }
}
