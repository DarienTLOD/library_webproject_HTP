package by.htp4.bitreight.library.dao.impl;

import by.htp4.bitreight.library.dao.OrderDAO;
import by.htp4.bitreight.library.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SuppressWarnings("Duplicates")
public class SQLOrderDAO implements OrderDAO {

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
    public boolean create(int userId, int bookId) throws DAOException {
        Connection connection = null;

        final String decrementBooksCountSql = "UPDATE books SET count = count - 1 WHERE id = ?;";

        final String insertNewOrderSql = "INSERT INTO orders(users_id, books_id) VALUES(?,?);";

        try {
            connection = getConnection();

            //disable autocommit to start new transaction
            connection.setAutoCommit(false);

            PreparedStatement prepStatementUpdate = connection.prepareStatement(decrementBooksCountSql);
            prepStatementUpdate.setInt(1, bookId);
            prepStatementUpdate.executeUpdate();

            PreparedStatement prepStatementInsert = connection.prepareStatement(insertNewOrderSql);
            prepStatementInsert.setInt(1, userId);
            prepStatementInsert.setInt(2, bookId);
            prepStatementInsert.executeUpdate();

            //if everything is OK, commit transaction
            connection.commit();

        } catch (SQLException ex1) {
            //if there is any error, rollback transaction
            try {
                connection.rollback();
            } catch (SQLException ex2) {
                throw new DAOException(ex2.getMessage());
            }
            throw new DAOException(ex1.getErrorCode() + "");

        } finally {
            closeConnection(connection);
        }

        return true;
    }

    @Override
    public boolean delete(int userId, int bookId) throws DAOException {
        Connection connection = null;

        final String incrementBooksCountSql = "UPDATE books SET count = count + 1 WHERE id = ?;";

        final String deleteOrderSql = "DELETE FROM orders WHERE users_id = ? AND books_id = ?;";

        try {
            connection = getConnection();

            //disable autocommit to start new transaction
            connection.setAutoCommit(false);

            PreparedStatement prepStatementUpdate = connection.prepareStatement(incrementBooksCountSql);
            prepStatementUpdate.setInt(1, bookId);
            prepStatementUpdate.executeUpdate();

            PreparedStatement prepStatementDelete = connection.prepareStatement(deleteOrderSql);
            prepStatementDelete.setInt(1, userId);
            prepStatementDelete.setInt(2, bookId);
            prepStatementDelete.executeUpdate();

            //if everything is OK, commit transaction
            connection.commit();
        } catch (SQLException ex1) {
            //if there is any error, rollback transaction
            try {
                connection.rollback();
            } catch (SQLException ex2) {
                throw new DAOException(ex2.getMessage());
            }
            throw new DAOException(ex1.getErrorCode() + "");

        } finally {
            closeConnection(connection);
        }

        return true;
    }
}
