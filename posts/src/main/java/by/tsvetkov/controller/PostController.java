package by.tsvetkov.controller;

import by.tsvetkov.dto.CreatePostDto;
import by.tsvetkov.dto.PostDto;
import by.tsvetkov.service.PostService;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void add(@RequestBody @Validated CreatePostDto createPostDto) {
        log.info("POST - /posts - {}", createPostDto);
        postService.add(createPostDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("DELETE - /posts/{}", id);
        postService.delete(id);
    }

    @PatchMapping()
    public void edit(@RequestBody PostDto postDto) {
        log.info("PATCH - /posts {}", postDto);
        postService.edit(postDto);
    }

    @GetMapping("/count")
    public long getPostsCount() {
        log.info("GET - /posts/count");
        return postService.postsCount();
    }

    @GetMapping
    public List<PostDto> getPosts(@RequestParam(defaultValue = "0") @PositiveOrZero Integer from, @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("GET - /posts");
        return postService.allPosts(from, size);
    }

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable Long id) {
        log.info("GET - /posts/{}", id);
        return postService.getById(id);
    }
}
