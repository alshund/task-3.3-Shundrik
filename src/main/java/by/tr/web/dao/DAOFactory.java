package by.tr.web.dao;

import by.tr.web.dao.impl.XMLPersonalAffairsDAO;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final PersonalAffairsDAO paDAO = new XMLPersonalAffairsDAO();

    public static DAOFactory getInstance() {
        return instance;
    }

    public PersonalAffairsDAO getXMLPersonalAffairsDAO() {
        return paDAO;
    }

    private DAOFactory() {}
}
