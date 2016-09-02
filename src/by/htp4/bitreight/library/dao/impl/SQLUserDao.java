package by.htp4.bitreight.library.dao.impl;

import by.htp4.bitreight.library.bean.User;
import by.htp4.bitreight.library.dao.UserDAO;
import by.htp4.bitreight.library.dao.exception.DAOException;

import java.sql.*;

public class SQLUserDAO implements UserDAO {

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
    public boolean create(User user) throws DAOException {
        return false;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws DAOException {
        return null;
    }

    @Override
    public boolean delete(int userId) {
        return false;
    }
}
