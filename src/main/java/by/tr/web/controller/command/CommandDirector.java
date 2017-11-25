package by.tr.web.controller.command;

import by.tr.web.controller.command.impl.*;
import by.tr.web.domain.Parameters;

import java.util.HashMap;
import java.util.Map;

public class CommandDirector {
    private Map<String, Command> commands = new HashMap<>();

    public CommandDirector() {

        commands.put(Parameters.SAX.name(), new ParseSAX());
        commands.put(Parameters.STAX.name(), new ParseStAX());
        commands.put(Parameters.DOM.name(), new ParseDOM());
        commands.put(Parameters.NEXT_PAGE.name(), new NextPage());
        commands.put(Parameters.PREVIOUS_PAGE.name(), new PreviousPage());
    }

    public Command getCommand(String commandName) {

        return commands.get(commandName);
    }
}
