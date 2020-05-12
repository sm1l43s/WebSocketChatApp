package by.brausov.service;

import by.brausov.model.entities.Post;

import java.util.List;

public interface PostService {

    List<Post> allPosts(int page);
    void add(Post post);
    void delete(Post post);
    void edit(Post post);
    int postsCount();
    Post getById(int id);

}
