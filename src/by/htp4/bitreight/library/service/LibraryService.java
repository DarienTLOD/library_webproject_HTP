package by.htp4.bitreight.library.service;

import by.htp4.bitreight.library.bean.Book;
import by.htp4.bitreight.library.service.exception.ServiceException;

import java.util.List;

public interface LibraryService {
    List<String> getBookCategories() throws ServiceException;
    List<Book> getBooksOfCategoryAndSortBy(String categoryName, String sortType) throws ServiceException;
    List<Book> searchForBooksAndSortBy(String searchString, String sortType) throws ServiceException;
}
