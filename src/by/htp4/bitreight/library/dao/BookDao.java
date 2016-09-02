package by.htp4.bitreight.library.dao;

import by.htp4.bitreight.library.bean.Book;
import by.htp4.bitreight.library.dao.exception.DAOException;

import java.util.List;

public interface BookDAO {
    boolean create(Book book) throws DAOException;
    Book findBookById(int bookId) throws DAOException;
    boolean update(Book book) throws DAOException;
    boolean delete(int bookId) throws DAOException;
    List<String> findBookCategories() throws DAOException;
    List<Book> findBooksBySearchAndOrderBy(String searchString, String columnName,
                                                                boolean ascending) throws DAOException;
    List<Book> findBooksByCategoryAndOrderBy(String bookCategory, String orderByColumnName,
                                                                  boolean ascending) throws DAOException;
}
