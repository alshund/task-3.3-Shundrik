package by.tr.web.dao.exception;

public class DAOException extends Exception {
    private static final long serialVersionUID = 7292333075237311360L;

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Exception e) {
        super(message, e);
    }

    public DAOException(Exception e) {
        super(e);
    }
}
