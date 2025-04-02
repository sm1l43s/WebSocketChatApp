package by.tsvetkov.service;

import by.tsvetkov.dto.UserDto;

public interface UserService {

    UserDto getUserInfo(String email);
}
