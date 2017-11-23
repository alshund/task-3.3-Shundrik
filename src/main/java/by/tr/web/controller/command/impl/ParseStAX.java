package by.tr.web.controller.command.impl;

import by.tr.web.controller.command.Command;
import by.tr.web.domain.Parameters;
import by.tr.web.service.PersonalAffairsService;
import by.tr.web.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ParseStAX implements Command {
    private PersonalAffairsService dpService;

    public ParseStAX() {

        ServiceFactory instance = ServiceFactory.getInstance();
        dpService = instance.getDataProcessingService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        dpService.getStudentList(Parameters.StAX.name());
    }
}
