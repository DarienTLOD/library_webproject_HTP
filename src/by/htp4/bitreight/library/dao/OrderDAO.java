package by.htp4.bitreight.library.dao;

import by.htp4.bitreight.library.dao.exception.DAOException;

public interface OrderDAO {
    boolean create(int userId, int bookId) throws DAOException;
    boolean delete(int userId, int bookId) throws DAOException;
}
