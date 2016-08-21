package by.htp4.bitreight.library.dao;

import by.htp4.bitreight.library.bean.User;

public interface CommonDao {
    User authorization(String login, String password);
    boolean registration(User user);
}
