package by.htp4.bitreight.library.service;

import by.htp4.bitreight.library.service.impl.AuthorizationServiceImpl;
import by.htp4.bitreight.library.service.impl.LibraryServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private LibraryService libraryService = new LibraryServiceImpl();
    private AuthorizationService authorizationService = new AuthorizationServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public LibraryService getLibraryService() {
        return libraryService;
    }

    public AuthorizationService getAuthorizationService() {
        return authorizationService;
    }
}
