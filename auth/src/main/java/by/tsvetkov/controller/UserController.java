package by.tsvetkov.controller;

import by.tsvetkov.dto.UserDto;
import by.tsvetkov.service.UserService;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> getUserInfo (@RequestParam @Email String email) {
        return ResponseEntity.ok(userService.getUserInfo(email));
    }
}
