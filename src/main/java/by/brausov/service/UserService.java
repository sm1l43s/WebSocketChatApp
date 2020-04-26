package by.brausov.service;

import by.brausov.model.entities.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    void add(User user);
    void delete(User user);
    void edit(User user);
    User getById(int id);
    Boolean checkUser(User user);
    User getByLoginAndPassword(User user);
}
