package by.htp4.bitreight.library.service.impl;

import by.htp4.bitreight.library.bean.Book;
import by.htp4.bitreight.library.dao.BookDao;
import by.htp4.bitreight.library.dao.DaoFactory;
import by.htp4.bitreight.library.dao.exception.DAOException;
import by.htp4.bitreight.library.service.BookCategory;
import by.htp4.bitreight.library.service.LibraryService;
import by.htp4.bitreight.library.service.SortType;

import java.util.List;

public class LibraryServiceImpl implements LibraryService {
    @Override
    public List<Book> getBooksOfCategoryAndSortBy(String categoryName, String sortName) {
        BookCategory bookCategory = null;
        SortType sortType = null;

        try {
            bookCategory = BookCategory.valueOf(categoryName);

        } catch (IllegalArgumentException | NullPointerException e) {
            bookCategory = BookCategory.ALL;
        }

        try {
            sortType = SortType.valueOf(sortName);

        } catch (IllegalArgumentException | NullPointerException e) {
            sortType = SortType.NONE;
        }

        DaoFactory daoFactory = DaoFactory.getInstance();
        BookDao bookDao = daoFactory.getBookDao();

        List<Book> books = null;

        try {
            books = bookDao.getBooksOfCategoryAndSortBy(bookCategory, sortType);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public List<Book> searchForBooksAndSortBy(String searchString, String sortName) {
        return null;
    }
}
