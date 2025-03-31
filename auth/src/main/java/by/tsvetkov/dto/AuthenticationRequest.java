package by.tsvetkov.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank(message = "Parameter 'password' can not be null or empty")
    @Pattern(
            regexp = "^(?=.*[a-zа-я])(?=.*[A-ZА-Я])(?=.*\\d)(?=.*[!@#$%])[A-ZА-Яa-zа-я\\d!@#$%]{6,20}$",
            message = "Incorrect password.")
    private String password;
}
