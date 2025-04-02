package by.tsvetkov.mapper.impl;

import by.tsvetkov.dto.CreatePostDto;
import by.tsvetkov.dto.PostDto;
import by.tsvetkov.mapper.PostMapper;
import by.tsvetkov.model.Post;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.processing.Generated;
import java.util.List;

@Generated(value = "org.mapstruct.ap.MappingProcessor")
public abstract class PostMapperDecorator implements PostMapper {

    @Autowired
    private PostMapper delegate;

    @Override
    public PostDto toPostDto(Post post) {
        return delegate.toPostDto(post);
    }

    @Override
    public List<PostDto> toPostResponseList(List<Post> posts) {
        return delegate.toPostResponseList(posts);
    }

    @Override
    public Post toPost(CreatePostDto postDto) {
        return delegate.toPost(postDto);
    }

    @Override
    public Post mapPostToUpdate(PostDto postDto, Post post) {

        Post newPost = new Post();
        if (!postDto.getDescription().isBlank()) {
            newPost.setDescription(postDto.getDescription());
        } else {
            newPost.setDescription(post.getDescription());
        }

        if (!postDto.getTitle().isBlank()) {
            newPost.setTitle(postDto.getTitle());
        } else {
            newPost.setTitle(post.getTitle());
        }

        if (post.getUserId() != null) {
            newPost.setUserId(post.getUserId());
        }
        newPost.setId(postDto.getId());

        return newPost;
    }
}
