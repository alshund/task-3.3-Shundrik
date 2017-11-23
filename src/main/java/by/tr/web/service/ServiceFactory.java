package by.tr.web.service;

import by.tr.web.service.impl.ParseService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final PersonalAffairsService dpService = new ParseService();

    public static ServiceFactory getInstance() {

        return instance;
    }

    public PersonalAffairsService getDataProcessingService() {

        return dpService;
    }

    private ServiceFactory() {}
}
