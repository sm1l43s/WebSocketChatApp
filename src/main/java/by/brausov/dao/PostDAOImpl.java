package by.brausov.dao;

import by.brausov.model.entities.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDAOImpl implements PostDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Post> allPosts(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Post ").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    public void add(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(post);
    }

    @Override
    public void delete(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(post);
    }

    @Override
    public void edit(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.update(post);
    }

    @Override
    public int postsCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Post ", Number.class).getSingleResult().intValue();
    }

    @Override
    public Post getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Post.class, id);
    }
}
