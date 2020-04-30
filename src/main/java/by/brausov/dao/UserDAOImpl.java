package by.brausov.dao;

import by.brausov.model.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static Map<Integer, User> users = new HashMap<>();

    private SessionFactory sessionFactory;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> allUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").list();
    }

    @Override
    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        session.persist(user);
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    public void edit(User user) {
       User user1 = getById(user.getId());
       user1.setName(user.getName());
       Session session = sessionFactory.getCurrentSession();
       session.update(user1);

    }

    @Override
    public User getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User user left join fetch user.role where user.email = :email");
        query.setParameter("email", email);
        return  (User) query.uniqueResult();
    }

    @Override
    public void setStatusOnline(User user) {
        Session session = sessionFactory.getCurrentSession();
        user.setStatus("online");
        session.update(user);
    }

    @Override
    public void setStatusOffline(User user) {
        Session session = sessionFactory.getCurrentSession();
        System.out.println("qwerty: " + user);
        User user1 = getById(user.getId());
        user1.setStatus("offline");
        session.update(user1);
    }

    @Override
    public List<User> allOnlineUsers() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where status=:status");
        query.setParameter("status", "online");
        return query.list();
    }
}
