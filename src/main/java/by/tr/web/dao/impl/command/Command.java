package by.tr.web.dao.impl.command;

import by.tr.web.domain.Student;

import java.util.List;

public interface Command {
    List<Student> execute();
}
