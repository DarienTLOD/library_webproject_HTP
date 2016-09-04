package by.htp4.bitreight.library.dao.impl;

import by.htp4.bitreight.library.bean.Book;
import by.htp4.bitreight.library.dao.BookDAO;
import by.htp4.bitreight.library.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLBookDAO implements BookDAO {

    private static final String COLUMN_NAME_ID = "id";
    private static final String COLUMN_NAME_TITLE = "title";
    private static final String COLUMN_NAME_AUTHOR = "author";
    private static final String COLUMN_NAME_DESCRIPTION = "description";
    private static final String COLUMN_NAME_YEAR = "year";
    private static final String COLUMN_NAME_PRICE = "price";
    private static final String COLUMN_NAME_ISBN = "isbn";
    private static final String COLUMN_NAME_COUNT = "count";
    private static final String COLUMN_NAME_CATEGORY = "category_name";

    private static final String FIND_BOOKS_SQL = "SELECT books.id, books.title, books.author, " +
                                                 "books.description, books.year, " +
                                                 "books.price, books.isbn, books.count, " +
                                                 "book_categories.name as category_name " +
                                                 "FROM books JOIN book_categories " +
                                                 "ON books.book_categories_id = book_categories.id";

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
                books.add(getBookData(rs));
            }

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        return books;
    }

    private Book getBookData(ResultSet rs) throws DAOException {
        Book book = new Book();

        try {
            book.setId(rs.getInt(COLUMN_NAME_ID));
            book.setTitle(rs.getString(COLUMN_NAME_TITLE));
            book.setAuthor(rs.getString(COLUMN_NAME_AUTHOR));
            book.setDescription(rs.getString(COLUMN_NAME_DESCRIPTION));
            book.setYear(rs.getInt(COLUMN_NAME_YEAR));
            book.setPrice(rs.getInt(COLUMN_NAME_PRICE));
            book.setIsbn(rs.getString(COLUMN_NAME_ISBN));
            book.setCount(rs.getInt(COLUMN_NAME_COUNT));
            book.setCategory(rs.getString(COLUMN_NAME_CATEGORY));

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        return book;
    }

    private String constructOrderByClause(String columnName, boolean ascending) {
        StringBuilder sql = new StringBuilder(" ORDER BY ");

        switch(columnName) {
            case COLUMN_NAME_PRICE:
                sql.append(COLUMN_NAME_PRICE);
                break;
            case COLUMN_NAME_YEAR:
                sql.append(COLUMN_NAME_YEAR);
                break;
        }

        if(ascending) {
            sql.append(" ASC");
        } else {
            sql.append(" DESC");
        }

        return sql.toString();
    }

    @Override
    public boolean create(Book book) throws DAOException {
        Connection connection = null;
        PreparedStatement prepStatement = null;

        String sql = "INSERT INTO books (title, author, description, year, price, isbn, count, book_categories_id) " +
                        "VALUES (?,?,?,?,?,?,?,(SELECT id FROM book_categories WHERE name = ?));";
        try {
            connection = getConnection();

            prepStatement = connection.prepareStatement(sql);

            prepStatement.setString(1, book.getTitle());
            prepStatement.setString(2, book.getAuthor());
            prepStatement.setString(3, book.getDescription());
            prepStatement.setInt(4, book.getYear());
            prepStatement.setInt(5, book.getPrice());
            prepStatement.setString(6, book.getIsbn());
            prepStatement.setInt(7, book.getCount());
            prepStatement.setString(8, book.getCategory());

            prepStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();//invalid category
        } finally {
            closeConnection(connection);
        }

        return true;
    }

    @Override
    public Book findBookById(int bookId) throws DAOException {
        Book book = null;

        Connection connection = null;
        PreparedStatement prepStatement = null;

        String sql = FIND_BOOKS_SQL + " WHERE books.id = ?";

        try {
            connection = getConnection();

            prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, bookId);

            ResultSet resultSet = prepStatement.executeQuery();
            if(resultSet.next()) {
                book = getBookData(resultSet);
            }

            return book;

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public boolean update(Book book) throws DAOException {
        Connection connection = null;
        PreparedStatement prepStatement = null;

        String sql = "UPDATE books SET title = ?, author = ?, description = ?, year = ?, price = ?, isbn = ?, " +
                     "count = ?, book_categories_id = (SELECT id FROM book_categories WHERE name = ?) " +
                     "WHERE id = ?";

        try {
            connection = getConnection();

            prepStatement = connection.prepareStatement(sql);

            prepStatement.setString(1, book.getTitle());
            prepStatement.setString(2, book.getAuthor());
            prepStatement.setString(3, book.getDescription());
            prepStatement.setInt(4, book.getYear());
            prepStatement.setInt(5, book.getPrice());
            prepStatement.setString(6, book.getIsbn());
            prepStatement.setInt(7, book.getCount());
            prepStatement.setString(8, book.getCategory());
            prepStatement.setInt(9, book.getId());

            prepStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return true;
    }

    @Override
    public boolean delete(int bookId) throws DAOException {
        Connection connection = null;
        PreparedStatement prepStatement = null;

        String sql = "DELETE FROM books WHERE id = ?";

        try {
            connection = getConnection();

            prepStatement = connection.prepareStatement(sql);
            prepStatement.setInt(1, bookId);

            prepStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            closeConnection(connection);
        }

        return true;
    }

    @Override
    public List<String> findBookCategories() throws DAOException {
        Connection connection = null;
        Statement statement = null;

        final String sql = "SELECT * FROM book_categories;";

        try {
            connection = getConnection();

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<String> categories = new ArrayList<>();
            while(resultSet.next()) {
                categories.add(resultSet.getString(2));
            }

            return categories;

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Book> findBooksByCategoryAndOrderBy(String bookCategory,
                                                    String orderByColumnName,
                                                    boolean ascending) throws DAOException {
        Connection connection = null;
        PreparedStatement prepStatement = null;

        StringBuilder sqlBuilder =
                new StringBuilder(FIND_BOOKS_SQL);

        if(bookCategory != null) {
            sqlBuilder.insert(0, "SELECT * FROM (")
                      .append(") temp WHERE temp.category_name = ?");
        }

        if(orderByColumnName != null) {
            sqlBuilder.append(constructOrderByClause(orderByColumnName, ascending));
        }

        try {
            connection = getConnection();

            prepStatement = connection.prepareStatement(sqlBuilder.toString());
            if(prepStatement.getParameterMetaData()
                            .getParameterCount() != 0) {
                prepStatement.setString(1, bookCategory);
            }

            ResultSet resultSet = prepStatement.executeQuery();
            return extractBooks(resultSet);

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Book> findBooksBySearchAndOrderBy(String searchString,
                                                  String orderByColumnName,
                                                  boolean ascending) throws DAOException {

        Connection connection = null;
        PreparedStatement prepStatement = null;

        StringBuilder sqlBuilder =
                new StringBuilder("SELECT books.id, books.title, books.author, " +
                                  "books.description, books.year, " +
                                  "books.price, books.isbn, books.count, " +
                                  "book_categories.name as category_name " +
                                  "FROM books JOIN book_categories " +
                                  "ON books.book_categories_id = book_categories.id " +
                                  "WHERE MATCH(author, title, description) AGAINST(?)");

        if(orderByColumnName != null) {
            sqlBuilder.append(constructOrderByClause(orderByColumnName, ascending));
        }

        try {
            connection = getConnection();

            prepStatement = connection.prepareStatement(sqlBuilder.toString());
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