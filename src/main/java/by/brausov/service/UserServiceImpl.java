package by.brausov.service;

import by.brausov.dao.UserDAO;
import by.brausov.model.entities.Role;
import by.brausov.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public List<User> allUsers() {
        return userDAO.allUsers();
    }

    @Transactional
    public List<User> allUsers(int page) {
        return userDAO.allUsers(page);
    }

    @Transactional
    public List<User> allUserByRole(Role role) {
        return userDAO.allUserByRole(role);
    }

    @Transactional
    public List<User> allUserByStatus(String status) {
        return userDAO.allUserByStatus(status);
    }

    @Transactional
    public List<User> allUserByBlocked(Boolean blocked) {
        return userDAO.allUserByBlocked(blocked);
    }

    @Transactional
    public List<User> search(String string) {
        return userDAO.search(string);
    }

    @Transactional
    public void add(User user) {
        userDAO.add(user);
    }

    @Transactional
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Transactional
    public void edit(User user) {
        userDAO.edit(user);
    }

    @Transactional
    public User getById(int id) {
        return userDAO.getById(id);
    }

    @Transactional
    public User getByEmail(String email) {
        return userDAO.getByEmail(email);
    }

    @Transactional
    public void setStatusOnline(User user) { userDAO.setStatusOnline(user);
    }

    @Transactional
    public void setStatusOffline(User user) {
        userDAO.setStatusOffline(user);
    }

    @Transactional
    public int usersCount() {
        return userDAO.usersCount();
    }
}
