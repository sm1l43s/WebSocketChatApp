package by.tsvetkov.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/*
 * DTO (Data Transfer Object) для создания поста.
 */
@Getter
@Setter
public class CreatePostDto {

    /*
     * Заголовок поста.
     * Это обязательное поле, не должно быть пустым.
     */
    @NotBlank
    private String title;

    /*
     * Описание поста.
     * Это также обязательное поле, не должно быть пустым.
     * Длина описания должна быть в пределах от 1 до 100000 символов.
     */
    @NotBlank
    @Size(min = 1, max = 100000)
    private String description;

    /*
     * Уникальный идентификатор пользователя, создавшего пост.
     */
    private Long userId;
}
