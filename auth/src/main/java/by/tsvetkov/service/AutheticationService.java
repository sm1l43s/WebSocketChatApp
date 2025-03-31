package by.tsvetkov.service;

import by.tsvetkov.dto.AuthenticationRequest;
import by.tsvetkov.dto.AuthenticationResponse;
import by.tsvetkov.dto.RegisterRequest;

public interface AutheticationService {

    AuthenticationResponse register(RegisterRequest registerRequest);
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
