package by.tsvetkov.service.impl;

import by.tsvetkov.dto.UserDto;
import by.tsvetkov.exception.NotFoundException;
import by.tsvetkov.mapper.UserMapper;
import by.tsvetkov.model.User;
import by.tsvetkov.repository.UserRepository;
import by.tsvetkov.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUserInfo(String email) {

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()) {
            return userMapper.toUserDto(user.get());
        } else {
            throw new NotFoundException("User not found, email: " + email);
        }
    }
}
