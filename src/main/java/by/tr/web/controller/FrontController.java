package by.tr.web.controller;

import by.tr.web.controller.command.Command;
import by.tr.web.controller.command.CommandDirector;
import by.tr.web.domain.Parameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private CommandDirector commandDirector = new CommandDirector();
    private Command command;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName = req.getParameter(Parameters.COMMAND.name().toLowerCase());
        command = commandDirector.getCommand(commandName);
        command.execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName = req.getParameter(Parameters.COMMAND.name().toLowerCase());
        command = commandDirector.getCommand(commandName);
        command.execute(req, resp);
    }
}
