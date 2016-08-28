package by.htp4.bitreight.library.dao;

import by.htp4.bitreight.library.bean.Book;
import by.htp4.bitreight.library.dao.exception.DAOException;

import java.util.List;

public interface BookDao {
    boolean addBook(Book book) throws DAOException;
    Book getBookById(int bookId) throws DAOException;
    boolean editBook(Book book) throws DAOException;
    boolean deleteBook(int bookId) throws DAOException;
    boolean addBookOrder(int userId, int bookId) throws DAOException;
    boolean removeBookOrder(int userId, int bookId) throws DAOException;
    List<Book> getBooksBySearchAndSortBy(String searchString, SortType sortType) throws DAOException;
    List<Book> getBooksOfCategoryAndSortBy(BookCategory bookCategory, SortType sortType) throws DAOException;
}
