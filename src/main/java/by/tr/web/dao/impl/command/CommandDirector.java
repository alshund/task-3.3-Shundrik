package by.tr.web.dao.impl.command;

import by.tr.web.dao.impl.command.impl.ParseDOM;
import by.tr.web.dao.impl.command.impl.ParseSAX;
import by.tr.web.dao.impl.command.impl.ParseStAX;
import by.tr.web.domain.Parameters;

import java.util.HashMap;
import java.util.Map;

public class CommandDirector {
    private Map<String, Command> commands = new HashMap<>();

    public CommandDirector() {

        commands.put(Parameters.SAX.name(), new ParseSAX());
        commands.put(Parameters.StAX.name(), new ParseStAX());
        commands.put(Parameters.DOM.name(), new ParseDOM());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
