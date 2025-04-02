package by.tsvetkov.mapper.impl;

import by.tsvetkov.dto.RegisterRequest;
import by.tsvetkov.dto.UserDto;
import by.tsvetkov.mapper.UserMapper;
import by.tsvetkov.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.processing.Generated;

@Generated(value = "org.mapstruct.ap.MappingProcessor")
public abstract class UserMapperDecorator implements UserMapper {

    @Autowired
    private UserMapper delegate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User toUser(RegisterRequest registerRequest) {
        User user = delegate.toUser(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        return user;
    }

    @Override
    public UserDto toUserDto(User user) {
        return delegate.toUserDto(user);
    }
}
