package by.tsvetkov.mapper;

import by.tsvetkov.dto.RegisterRequest;
import by.tsvetkov.dto.UserDto;
import by.tsvetkov.mapper.impl.UserMapperDecorator;
import by.tsvetkov.model.User;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", constant = "USER")
    @Mapping(source = "registerRequest.email", target = "email")
    @Mapping(source = "registerRequest.name", target = "name")
    @Mapping(target = "status", constant = "active")
    @Mapping(target = "blocked", constant = "false")
    User toUser(RegisterRequest registerRequest);

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.status", target = "status")
    @Mapping(source = "user.blocked", target = "blocked")
    @Mapping(source = "user.role", target = "role")
    UserDto toUserDto(User user);
}
