package by.htp4.bitreight.library.dao;

import by.htp4.bitreight.library.bean.User;
import by.htp4.bitreight.library.dao.exception.DAOException;

public interface UserDAO {
    boolean create(User user) throws DAOException;
    User findUserByLoginAndPassword(String login, String password) throws DAOException;
    boolean delete(int userId) throws DAOException;
}
