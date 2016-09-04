package by.htp4.bitreight.library.dao.impl;

import by.htp4.bitreight.library.bean.User;
import by.htp4.bitreight.library.dao.UserDAO;
import by.htp4.bitreight.library.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    private List<User> extractUsers(ResultSet rs) throws DAOException {
        List<User> users = new ArrayList<>();

        try {
            while(rs.next()) {
                users.add(getUserData(rs));
            }

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        return users;
    }

    private User getUserData(ResultSet rs) throws DAOException {
        User user = new User();

        try {
            user.setId(rs.getInt(1));
            user.setLogin(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setName(rs.getString(4));
            user.setSurname(rs.getString(5));
            user.setRole(rs.getString(6));

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        return user;
    }

    @Override
    public boolean create(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement prepStatement = null;

        String sql = "INSERT INTO users (login, password, name, surname, roles_id) " +
                     "VALUES (?,?,?,?,(SELECT id FROM roles WHERE name = 'user'));";

        try {
            connection = getConnection();

            prepStatement = connection.prepareStatement(sql);

            prepStatement.setString(1, user.getLogin());
            prepStatement.setString(2, user.getPassword());
            prepStatement.setString(3, user.getName());
            prepStatement.setString(4, user.getSurname());

            prepStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return true;
    }

    //unnecessary at this moment
    @Override
    public List<User> findUsers() throws DAOException {
        return null;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws DAOException {
        User user = null;

        Connection connection = null;
        PreparedStatement prepStatement = null;

        String sql = "SELECT users.id, users.login, users.password, users.name, " +
                     "users.surname, roles.name As role_name FROM users " +
                     "JOIN roles ON users.roles_id = roles.id " +
                     "WHERE login = ? AND password = ?;";

        try {
            connection = getConnection();

            prepStatement = connection.prepareStatement(sql);

            prepStatement.setString(1, login);
            prepStatement.setString(2, password);

            ResultSet resultSet = prepStatement.executeQuery();
            if(resultSet.next()) {
                user = getUserData(resultSet);
            }

            return user;

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    //unnecessary at this moment
    @Override
    public boolean delete(int userId) {
        return false;
    }
}
