package by.tsvetkov.mapper;

import by.tsvetkov.dto.CreatePostDto;
import by.tsvetkov.dto.PostDto;
import by.tsvetkov.mapper.impl.PostMapperDecorator;
import by.tsvetkov.model.Post;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@DecoratedWith(PostMapperDecorator.class)
public interface PostMapper {

    PostDto toPostDto(Post post);

    List<PostDto> toPostResponseList(List<Post> posts);

    Post toPost(CreatePostDto postDto);

    Post mapPostToUpdate(PostDto postDto, @MappingTarget Post post);
}
