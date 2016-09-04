package by.htp4.bitreight.library.command.impl;

import by.htp4.bitreight.library.bean.User;
import by.htp4.bitreight.library.command.Command;
import by.htp4.bitreight.library.service.AuthorizationService;
import by.htp4.bitreight.library.service.ServiceFactory;
import by.htp4.bitreight.library.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_SURNAME = "surname";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String name = request.getParameter(PARAM_NAME_NAME);
        String surname = request.getParameter(PARAM_NAME_SURNAME);

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AuthorizationService authorizationService = serviceFactory.getAuthorizationService();

        try {
            authorizationService.register(user);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            response.sendRedirect("/catalog");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
