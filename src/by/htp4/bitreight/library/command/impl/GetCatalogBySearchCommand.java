package by.htp4.bitreight.library.command.impl;

import by.htp4.bitreight.library.bean.Book;
import by.htp4.bitreight.library.command.Command;
import by.htp4.bitreight.library.service.LibraryService;
import by.htp4.bitreight.library.service.ServiceFactory;
import by.htp4.bitreight.library.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetCatalogBySearchCommand implements Command {

    private final static String PARAM_NAME_SEARCH_QUERY = "q";
    private static final String PARAM_NAME_SORT = "s";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String searchQuery = request.getParameter(PARAM_NAME_SEARCH_QUERY);
        String sortType = request.getParameter(PARAM_NAME_SORT);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();

        List<Book> books = null;

        try {
            books = libraryService.searchForBooksAndSortBy(searchQuery, sortType);
        } catch (ServiceException e) {
            //error message - "No search results!"
        }

        request.setAttribute("bookList", books);
        try {
            request.getRequestDispatcher("/WEB-INF/jsp/catalog.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
