package by.tsvetkov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import by.tsvetkov.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}