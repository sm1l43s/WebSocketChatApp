package by.tsvetkov.dto;

import lombok.Getter;
import lombok.Setter;

/*
 * DTO (Data Transfer Object) для представления поста..
 */
@Getter
@Setter
public class PostDto {

    /*
     * Уникальный идентификатор поста.
     */
    private Long id;

    /*
     * Заголовок поста.
     */
    private String title;

    /*
     * Описание поста.
     */
    private String description;

    /*
     * Уникальный идентификатор пользователя, создавшего пост.
     */
    private Long userId;
}
