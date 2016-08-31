package by.htp4.bitreight.library.command.impl;

import by.htp4.bitreight.library.bean.Book;
import by.htp4.bitreight.library.command.Command;
import by.htp4.bitreight.library.service.LibraryService;
import by.htp4.bitreight.library.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetCatalogCommand implements Command {

    private static final String PARAM_NAME_CATEGORY = "c";
    private static final String PARAM_NAME_SORT = "s";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        String category = request.getParameter(PARAM_NAME_CATEGORY);
        String sortType = request.getParameter(PARAM_NAME_SORT);

        //System.out.println(category);
        //System.out.println(sortType);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();

        List<Book> books = libraryService.getBooksOfCategoryAndSortBy(category, sortType);

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
