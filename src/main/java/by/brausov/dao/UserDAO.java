package by.brausov.dao;

import by.brausov.model.entities.User;

import java.util.List;

public interface UserDAO {

    List<User> allUsers();
    void add(User user);
    void delete(User user);
    void edit(User user);
    User getById(int id);

}
