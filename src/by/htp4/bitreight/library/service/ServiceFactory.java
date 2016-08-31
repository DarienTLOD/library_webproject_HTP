package by.htp4.bitreight.library.service;

import by.htp4.bitreight.library.service.impl.LibraryServiceImpl;

import java.util.Collection;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private LibraryService libraryService = new LibraryServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public LibraryService getLibraryService() {
        return libraryService;
    }

}
