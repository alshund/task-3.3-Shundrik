package by.tr.web.dao.impl;

import by.tr.web.dao.PersonalAffairsDAO;
import by.tr.web.dao.impl.command.Command;
import by.tr.web.dao.impl.command.CommandDirector;
import by.tr.web.domain.Student;

import java.util.List;

public class XMLPersonalAffairsDAO implements PersonalAffairsDAO {
    private CommandDirector commandDirector = new CommandDirector();
    private Command command;

    @Override
    public List<Student> parseXML(String xmlParserType) {

        command = commandDirector.getCommand(xmlParserType);
        return command.execute();
    }
}
