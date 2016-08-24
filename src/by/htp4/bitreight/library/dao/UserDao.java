package by.htp4.bitreight.library.dao;

import by.htp4.bitreight.library.bean.Book;
import by.htp4.bitreight.library.dao.exception.DAOException;

import java.util.List;

public interface UserDao {
    //List<Book> getBooks() throws DAOException;
    //List<Book> getBooksAndSortBy(SortType sortType) throws DAOException;
    List<Book> getBooksBySearchAndSortBy(String searchString, SortType sortType) throws DAOException;
    List<Book> getBooksOfCategoryAndSortBy(BookCategory bookCategory, SortType sortType) throws DAOException;
    boolean orderBook(int userId, int bookId) throws DAOException;
    boolean returnBook(int userId, int bookId) throws DAOException;
}
