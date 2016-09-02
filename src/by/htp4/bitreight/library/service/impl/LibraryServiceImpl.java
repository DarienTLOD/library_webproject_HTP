package by.htp4.bitreight.library.service.impl;

import by.htp4.bitreight.library.bean.Book;
import by.htp4.bitreight.library.dao.BookDAO;
import by.htp4.bitreight.library.dao.DAOFactory;
import by.htp4.bitreight.library.dao.exception.DAOException;
import by.htp4.bitreight.library.service.BookSortType;
import by.htp4.bitreight.library.service.LibraryService;
import by.htp4.bitreight.library.service.exception.ServiceException;

import java.util.List;

public class LibraryServiceImpl implements LibraryService {

    @Override
    public List<String> getBookCategories() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();

        List<String> categories = null;

        try {
            categories = bookDAO.findBookCategories();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public List<Book> getBooksOfCategoryAndSortBy(String categoryName, String sortType) {

        //check if selected category exists in the database
        List<String> categories = getBookCategories();
        if(!categories.contains(categoryName)) {
            categoryName = null;
        }

        BookSortType bookSortType = validateBookSortType(sortType);

        //check if selected sort type is valid
//        try {
//             bookSortType = BookSortType.valueOf(sortType.toUpperCase());
//
//        } catch (IllegalArgumentException | NullPointerException e) {
//            bookSortType = BookSortType.NONE;
//        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();

        List<Book> books = null;

        try {
            books = bookDAO.findBooksByCategoryAndOrderBy(categoryName, bookSortType.getFieldName(),
                                                                        bookSortType.isAscendingOrder());
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public List<Book> searchForBooksAndSortBy(String searchString, String sortType) throws ServiceException {
        if(searchString == null || searchString.trim().isEmpty()) {
            throw new ServiceException();
        }

        BookSortType bookSortType = validateBookSortType(sortType);

        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();

        List<Book> books = null;

        try {
            books = bookDAO.findBooksBySearchAndOrderBy(searchString, bookSortType.getFieldName(),
                                                                      bookSortType.isAscendingOrder());
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return books;
    }

    private BookSortType validateBookSortType(String sortType) {
        BookSortType bookSortType = null;

        try {
            bookSortType = BookSortType.valueOf(sortType.toUpperCase());

        } catch (IllegalArgumentException | NullPointerException e) {
            bookSortType = BookSortType.NONE;
        }

        return bookSortType;
    }
}
