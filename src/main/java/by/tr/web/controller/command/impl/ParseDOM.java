package by.tr.web.controller.command.impl;

import by.tr.web.controller.command.Command;
import by.tr.web.domain.FilePath;
import by.tr.web.domain.Parameters;
import by.tr.web.domain.Student;
import by.tr.web.service.PersonalAffairsService;
import by.tr.web.service.ServiceFactory;
import by.tr.web.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ParseDOM implements Command{
    private PersonalAffairsService dpService;
    private int firstPage = 0;
    private int recordsNumber = 2;

    public ParseDOM() {

        ServiceFactory instance = ServiceFactory.getInstance();
        dpService = instance.getDataProcessingService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {

        List<Student> studentList = dpService.getStudentList(Parameters.DOM.name(), firstPage, recordsNumber);
        setAttributes(request, studentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher(FilePath.RESULT_PAGE);
        dispatcher.forward(request, response);
    }

    private void setAttributes(HttpServletRequest request, List<Student> studentList) {

        request.getSession().setAttribute(Parameters.CURRENT_PAGE.name().toLowerCase(), firstPage);
        request.getSession().setAttribute(Parameters.RECORDS_NUMBER.name().toLowerCase(), recordsNumber);
        request.setAttribute(Parameters.PAGE.name().toLowerCase(), studentList);
    }
}
