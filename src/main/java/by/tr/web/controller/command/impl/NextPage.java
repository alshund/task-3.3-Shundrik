package by.tr.web.controller.command.impl;


import by.tr.web.controller.command.Command;
import by.tr.web.domain.FilePath;
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

public class NextPage implements Command {
    private PersonalAffairsService dpService;

    public NextPage() {

        ServiceFactory instance = ServiceFactory.getInstance();
        dpService = instance.getDataProcessingService();
    }


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (dpService.isPageValuable(getCurrentPage(request) + 1, getRecordsNumber(request))) {
            incrementCurrentPage(request);
        }
        setPage(request, getCurrentPage(request), getRecordsNumber(request));
        RequestDispatcher dispatcher = request.getRequestDispatcher(FilePath.RESULT_PAGE);
        dispatcher.forward(request, response);
    }

    private int getCurrentPage(HttpServletRequest request) {

        return (int) request.getSession().getAttribute(Parameters.CURRENT_PAGE.name().toLowerCase());
    }

    private int getRecordsNumber(HttpServletRequest request) {

        return (int) request.getSession().getAttribute(Parameters.RECORDS_NUMBER.name().toLowerCase());
    }

    private void incrementCurrentPage(HttpServletRequest request) {

        int previousPage = (int) request.getSession().getAttribute(Parameters.CURRENT_PAGE.name().toLowerCase());
        request.getSession().setAttribute(Parameters.CURRENT_PAGE.name().toLowerCase(), ++previousPage);
    }

    private void setPage(HttpServletRequest request, int currentPage, int recordsNumber) {

        List<Student> studentPage = dpService.getPage(currentPage, recordsNumber);
        request.setAttribute(Parameters.PAGE.name().toLowerCase(), studentPage);
    }
}
