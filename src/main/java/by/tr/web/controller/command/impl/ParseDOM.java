package by.tr.web.controller.command.impl;

import by.tr.web.controller.command.Command;
import by.tr.web.domain.Parameters;
import by.tr.web.domain.Student;
import by.tr.web.service.PersonalAffairsService;
import by.tr.web.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ParseDOM implements Command{
    private PersonalAffairsService dpService;

    public ParseDOM() {

        ServiceFactory instance = ServiceFactory.getInstance();
        dpService = instance.getDataProcessingService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Student> studentList = dpService.getStudentList(Parameters.DOM.name());
        request.setAttribute("page", studentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ParserResult.jsp");
        dispatcher.forward(request, response);
    }
}
