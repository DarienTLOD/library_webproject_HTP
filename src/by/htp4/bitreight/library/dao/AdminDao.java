package by.htp4.bitreight.library.dao;

import by.htp4.bitreight.library.bean.Book;

public interface AdminDao {

    //boolean addUser(User user);
    //boolean editUser(User user);
    boolean deleteUser(int userId);
    boolean addBook(Book book);
    //boolean editBook(Book book);
    boolean deleteBook(int bookId);
}
