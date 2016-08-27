package by.htp4.bitreight.library.dao;

import by.htp4.bitreight.library.bean.User;
import by.htp4.bitreight.library.dao.exception.DAOException;

public interface UserDao {
    boolean addUser(User user) throws DAOException;
    User getUserByLoginAndPassword(String login, String password) throws DAOException;
    boolean deleteUser(int userId);
}
