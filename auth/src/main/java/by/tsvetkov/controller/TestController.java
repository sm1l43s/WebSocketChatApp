package by.tsvetkov.controller;

import by.tsvetkov.service.AutheticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final AutheticationService autheticationService;

    @GetMapping()
    public String register() {
        return "Jr";
    }

}