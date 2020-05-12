package by.brausov.service;

import by.brausov.dao.PostDAO;
import by.brausov.model.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    @Transactional
    public List<Post> allPosts(int page) {
        return postDAO.allPosts(page);
    }

    @Transactional
    public void add(Post post) {
        postDAO.add(post);
    }

    @Transactional
    public void delete(Post post) {
        postDAO.delete(post);
    }

    @Transactional
    public void edit(Post post) {
        postDAO.edit(post);
    }

    @Transactional
    public int postsCount() {
        return postDAO.postsCount();
    }

    @Transactional
    public Post getById(int id) {
        return postDAO.getById(id);
    }
}
