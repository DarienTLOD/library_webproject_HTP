package by.htp4.bitreight.library.dao;

import by.htp4.bitreight.library.dao.impl.SQLBookDao;
import by.htp4.bitreight.library.dao.impl.SQLUserDao;

public class DaoFactory {

    private static final DaoFactory instance = new DaoFactory();

    private UserDao userDao = new SQLUserDao();
    private BookDao bookDao = new SQLBookDao();

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }
}
