package by.htp4.bitreight.library.service.impl;

import by.htp4.bitreight.library.bean.User;
import by.htp4.bitreight.library.dao.DAOFactory;
import by.htp4.bitreight.library.dao.UserDAO;
import by.htp4.bitreight.library.dao.exception.DAOException;
import by.htp4.bitreight.library.service.AuthorizationService;
import by.htp4.bitreight.library.service.exception.ServiceException;

public class AuthorizationServiceImpl implements AuthorizationService {

    @Override
    public User login(String login, String password) throws ServiceException {
        if(!validateCredentials(login, password)) {
            throw new ServiceException();
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        User user = null;

        try {
            user = userDAO.findUserByLoginAndPassword(login, password);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean register(User user) throws ServiceException {
        String login = user.getLogin();
        String password = user.getPassword();

        if(!validateCredentials(login, password)) {
            throw new ServiceException();
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            userDAO.create(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return true;
    }

    private boolean validateCredentials(String login, String password) {
        if (login == null || login.length() < 6) {
            return false;
        }

        if (password == null || password.length() < 6) {
            return false;
        }

        return true;
    }
}
