package by.htp4.bitreight.library.dao.impl;

import by.htp4.bitreight.library.bean.User;
import by.htp4.bitreight.library.dao.UserDao;
import by.htp4.bitreight.library.dao.exception.DAOException;

import java.sql.*;

public class SQLUserDao implements UserDao {

    private Connection getConnection() throws DAOException {
        Connection connection = null;

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/library", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException(e.getMessage());
        }

        return connection;
    }

    private void closeConnection(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            //throw new DAOException(e.getMessage());
            //logging
        }
    }

    @Override
    public boolean addUser(User user) throws DAOException {
        return false;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws DAOException {
        return null;
    }

    @Override
    public boolean deleteUser(int userId) {
        return false;
    }
}
