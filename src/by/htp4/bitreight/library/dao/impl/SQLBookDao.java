package by.htp4.bitreight.library.dao.impl;

import by.htp4.bitreight.library.bean.Book;
import by.htp4.bitreight.library.service.BookCategory;
import by.htp4.bitreight.library.dao.BookDao;
import by.htp4.bitreight.library.service.SortType;
import by.htp4.bitreight.library.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class SQLBookDao implements BookDao {

    private final String getBooksBaseSql = "SELECT books.id, books.title, books.author, " +
                                            "books.description, books.year, " +
                                            "books.price, books.isbn, books.count, " +
                                            "book_categories.name as category_name " +
                                            "FROM books JOIN book_categories " +
                                            "ON books.book_categories_id = book_categories.id";

    private final String getBooksBySearchBaseSql = "SELECT books.id, books.title, books.author, " +
                                                    "books.description, books.year, " +
                                                    "books.price, books.isbn, books.count, " +
                                                    "book_categories.name as category_name " +
                                                    "FROM books JOIN book_categories " +
                                                    "ON books.book_categories_id = book_categories.id " +
                                                    "WHERE MATCH(author, title, description) AGAINST(?)";

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

    private List<Book> extractBooks(ResultSet rs) throws DAOException {
        List<Book> books = new ArrayList<>();

        try {
            while(rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setDescription(rs.getString("description"));
                book.setYear(rs.getInt("year"));
                book.setPrice(rs.getInt("price"));
                book.setIsbn(rs.getString("isbn"));
                book.setCount(rs.getInt("count"));
                book.setCategory(rs.getString("category_name"));

                books.add(book);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        return books;
    }

    @Override
    public boolean addBook(Book book) throws DAOException {
        return false;
    }

    @Override
    public Book getBookById(int bookId) throws DAOException {
        return null;
    }

    @Override
    public boolean editBook(Book book) throws DAOException {
        return false;
    }

    @Override
    public boolean deleteBook(int bookId) throws DAOException {
        return false;
    }

    @Override
    public boolean addBookOrder(int userId, int bookId) throws DAOException {
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
    public boolean removeBookOrder(int userId, int bookId) throws DAOException {
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

    @Override
    public List<Book> getBooksOfCategoryAndSortBy(BookCategory bookCategory,
                                                  SortType sortType) throws DAOException {
        Connection connection = null;
        String sql = null;

        if(bookCategory != BookCategory.ALL) {
            sql = "SELECT * FROM (" + getBooksBaseSql + ") t " +
                    " WHERE t.category_name = " + "'" + bookCategory.toString().toLowerCase() + "' ";
        } else {
            sql = getBooksBaseSql;
        }

        if(sortType != SortType.NONE) {
            sql += " ORDER BY " + sortType.toString().replace("_", " ");
        }

        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            return extractBooks(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Book> getBooksBySearchAndSortBy(String searchString,
                                                SortType sortType) throws DAOException {
        Connection connection = null;
        String sql = null;

        if(sortType != SortType.NONE) {
            sql = getBooksBySearchBaseSql + " ORDER BY " + sortType.toString().replace("_", " ");
        } else {
            sql = getBooksBaseSql;
        }

        try {
            connection = getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(sql);
            prepStatement.setString(1, searchString);
            ResultSet resultSet = prepStatement.executeQuery();
            return extractBooks(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }
}
