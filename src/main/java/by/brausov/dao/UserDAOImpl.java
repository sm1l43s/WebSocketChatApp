package by.brausov.dao;

import by.brausov.model.entities.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static Map<Integer, User> users = new HashMap<>();

    static {
        User user = new User();
        user.setId(AUTO_ID.getAndIncrement() + 1);
        user.setName("Name1");
        user.setPassword("repona44");
        user.setStatus("online");
        users.put(user.getId(), user);
    }

    @Override
    public List<User> allUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void add(User user) {
        user.setId(AUTO_ID.getAndIncrement());
        users.put(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    @Override
    public void edit(User user) {
        users.put((int) user.getId(), user);
    }

    @Override
    public User getById(int id) {
        return users.get(id);
    }
}
