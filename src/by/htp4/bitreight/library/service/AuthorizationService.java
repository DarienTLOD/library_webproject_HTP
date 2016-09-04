package by.htp4.bitreight.library.service;

import by.htp4.bitreight.library.bean.User;
import by.htp4.bitreight.library.service.exception.ServiceException;

public interface AuthorizationService {
    User login(String login, String password) throws ServiceException;
    boolean register(User user) throws ServiceException;
}
