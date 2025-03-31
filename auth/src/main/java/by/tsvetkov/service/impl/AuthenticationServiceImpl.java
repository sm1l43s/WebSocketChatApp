package by.tsvetkov.service.impl;

import by.tsvetkov.config.JwtService;
import by.tsvetkov.dto.AuthenticationRequest;
import by.tsvetkov.dto.AuthenticationResponse;
import by.tsvetkov.dto.RegisterRequest;
import by.tsvetkov.exception.AlreadyExistsException;
import by.tsvetkov.mapper.UserMapper;
import by.tsvetkov.model.User;
import by.tsvetkov.repository.UserRepository;
import by.tsvetkov.service.AutheticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AutheticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {

        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new AlreadyExistsException("User with email = " + registerRequest.getEmail() + " already exists");
        }

        User user = userRepository.save(userMapper.toUser(registerRequest, passwordEncoder));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
