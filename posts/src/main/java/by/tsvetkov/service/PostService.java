package by.tsvetkov.service;

import by.tsvetkov.dto.CreatePostDto;
import by.tsvetkov.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> allPosts(int page, int size);
    void add(CreatePostDto postDto);
    void delete(Long id);
    void edit(PostDto postDto);
    long postsCount();
    PostDto getById(Long id);
}
