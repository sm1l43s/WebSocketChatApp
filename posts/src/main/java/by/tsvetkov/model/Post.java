package by.tsvetkov.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Представляет пост в приложении
 */
@Entity
@Table(name = "posts")
@NoArgsConstructor
@Getter
@Setter
public class Post {

    /*
     * Уникальный идентификатор поста, генерируемый автоматически.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Заголовок поста.
     */
    private String title;

    /*
     * Описание поста.
     * Максимальная длина описания составляет 100000 символов.
     */
    @Column(length = 100000)
    private String description;

    /*
     * Уникальный идентификатор пользователя, создавшего пост.
     */
    private Long userId;
}
