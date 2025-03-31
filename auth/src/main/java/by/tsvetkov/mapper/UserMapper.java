package by.tsvetkov.mapper;

import by.tsvetkov.dto.RegisterRequest;
import by.tsvetkov.dto.UserDto;
import by.tsvetkov.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "password", expression = "java(encoder.encode(registerRequest.getPassword()))")
    @Mapping(target = "role", constant = "USER")
    @Mapping(target = "email", source = "registerRequest.email")
    @Mapping(target = "name", source = "registerRequest.name")
    @Mapping(target = "status", constant = "active")
    @Mapping(target = "blocked", constant = "false")
    User toUser(RegisterRequest registerRequest, PasswordEncoder encoder);

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "status", source = "user.status")
    @Mapping(target = "blocked", source = "user.blocked")
    @Mapping(target = "role", source = "user.role")
    UserDto toUserDto(User user);
}
