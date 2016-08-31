package by.htp4.bitreight.library.service;

import by.htp4.bitreight.library.bean.Book;

import java.util.List;

public interface LibraryService {
    List<Book> getBooksOfCategoryAndSortBy(String category, String sortName);
    List<Book> searchForBooksAndSortBy(String searchString, String sortName);
}
