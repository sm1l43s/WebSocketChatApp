package by.tsvetkov.service.impl;

import by.tsvetkov.dto.CreatePostDto;
import by.tsvetkov.dto.PostDto;
import by.tsvetkov.exception.NotFoundException;
import by.tsvetkov.mapper.PostMapper;
import by.tsvetkov.repository.PostRepository;
import by.tsvetkov.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import by.tsvetkov.model.Post;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public List<PostDto> allPosts(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return postRepository.findAll(pageRequest).getContent().stream().map(postMapper::toPostDto).toList();
    }

    @Override
    @Transactional
    public void add(CreatePostDto postDto) {
        Post post = postMapper.toPost(postDto);
        postRepository.save(post);
    }

    @Override
    @Transactional
    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    @Transactional
    public void edit(PostDto postDto) {

        Post post = postRepository.findById(postDto.getId()).orElse(null);

        if (post != null) {
            Post updatedPost = postMapper.mapPostToUpdate(postDto, post);
            postRepository.save(updatedPost);
        } else {
            throw new NotFoundException("Post not found, id = " + postDto.getId());
        }
    }

    @Override
    public long postsCount() {
        return postRepository.count();
    }

    @Override
    public PostDto getById(Long id) {
        return postMapper.toPostDto(postRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Post not found, id = " + id)));
    }
}
