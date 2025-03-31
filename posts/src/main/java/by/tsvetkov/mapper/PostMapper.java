package by.tsvetkov.mapper;

import by.tsvetkov.dto.CreatePostDto;
import by.tsvetkov.dto.PostDto;
import by.tsvetkov.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {
    PostDto toPostDto(Post post);

    List<PostDto> toPostResponseList(List<Post> posts);

    Post toPost(CreatePostDto postDto);

    default Post mapPostToUpdate(PostDto postDto, Post post) {
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
