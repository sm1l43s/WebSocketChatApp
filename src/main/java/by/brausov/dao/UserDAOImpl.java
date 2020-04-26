package by.brausov.dao;

import by.brausov.coders.Encryptor;
import by.brausov.model.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
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
        session.persist(user);
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    public void edit(User user) {
       Session session = sessionFactory.getCurrentSession();
       session.update(user);
    }

    @Override
    public User getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public Boolean checkUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        boolean flag = false;
        User user1 = getByLoginAndPassword(user);

        if(user1 == null){
          flag = false;
        } else if(user.getName().equals(user1.getName())
                && Encryptor.encrypt(user.getPassword()).equals(user1.getPassword())) flag = true;

        return flag;
    }

    @Override
    public User getByLoginAndPassword(User user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User user left join fetch user.role where user.name = :username and  user.password = :password");
        query.setParameter("username", user.getName());
        query.setParameter("password", Encryptor.encrypt(user.getPassword()));
        return  (User) query.uniqueResult();
    }
}
