package by.htp4.bitreight.library.dao.impl;

import by.htp4.bitreight.library.bean.Book;
import by.htp4.bitreight.library.dao.BookCategory;
import by.htp4.bitreight.library.dao.SortType;
import by.htp4.bitreight.library.dao.UserDao;
import by.htp4.bitreight.library.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLUserDao implements UserDao {

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

    private void closeConnection(Connection connection) throws DAOException {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
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

//    @Override
//    public List<Book> getBooks() throws DAOException {
//        Connection connection = null;
//
//        try {
//            connection = getConnection();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(getBooksBaseSql);
//            return extractBooks(resultSet);
//        } catch (SQLException e) {
//            throw new DAOException(e.getMessage());
//        } finally {
//            closeConnection(connection);
//        }
//    }

//    @Override
//    public List<Book> getBooksAndSortBy(SortType sortType) throws DAOException {
//        Connection connection = null;
//        String sql = null;
//
//        if(sortType != SortType.NONE) {
//            sql = getBooksBaseSql + " ORDER BY " + sortType.toString().replace("_", " ");
//        } else {
//            sql = getBooksBaseSql;
//        }
//
//        try {
//            connection = getConnection();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//            return extractBooks(resultSet);
//        } catch (SQLException e) {
//            throw new DAOException(e.getMessage());
//        } finally {
//            closeConnection(connection);  //??
//        }
//    }

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
            closeConnection(connection); //??
        }
    }

    @Override
    public List<Book> getBooksBySearchAndSortBy(String searchString, SortType sortType) throws DAOException {
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
            closeConnection(connection);  //??
        }
    }

    @Override
    public boolean orderBook(int userId, int bookId) {
        return false;
    }

    @Override
    public boolean returnBook(int userId, int bookId) {
        return false;
    }
}
