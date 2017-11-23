package by.tr.web.controller.command;

import by.tr.web.controller.command.impl.ParseDOM;
import by.tr.web.controller.command.impl.ParseSAX;
import by.tr.web.controller.command.impl.ParseStAX;
import by.tr.web.domain.Parameters;

import java.util.Map;
import java.util.HashMap;

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
