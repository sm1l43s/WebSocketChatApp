package by.tsvetkov.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostDto {

    @NotBlank
    private String title;

    @NotBlank
    @Size(min = 1, max = 100000)
    private String description;

    private Long userId;
}
