package by.brausov.dao;

import by.brausov.model.entities.Role;
import by.brausov.model.entities.User;

import java.util.List;

public interface UserDAO {

    List<User> allUsers();
    List<User> allUsers(int page);
    List<User> allUserByRole(Role role);
    List<User> allUserByStatus(String status);
    List<User> allUserByBlocked(Boolean blocked);
    List<User> search(String string);
    void add(User user);
    void delete(User user);
    void edit(User user);
    User getById(int id);
    User getByEmail(String email);
    void setStatusOnline(User user);
    void setStatusOffline(User user);
    int usersCount();
}
