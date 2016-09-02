package by.htp4.bitreight.library.dao;

import by.htp4.bitreight.library.dao.impl.SQLBookDAO;
import by.htp4.bitreight.library.dao.impl.SQLUserDAO;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private UserDAO userDAO = new SQLUserDAO();
    private BookDAO bookDAO = new SQLBookDAO();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public BookDAO getBookDAO() {
        return bookDAO;
    }
}
