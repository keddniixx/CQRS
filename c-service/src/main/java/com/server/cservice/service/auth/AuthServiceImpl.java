package com.server.cservice.service.auth;

import com.server.cservice.service.client.ClientService;
import com.server.cservice.web.dto.LoginRequestDto;
import com.server.cservice.web.dto.LoginResponseDto;
import com.server.cservice.web.security.jwt.JwtProperties;
import com.server.cservice.web.security.jwt.TokenType;
import com.server.cservice.web.security.lib.TokenParameters;
import com.server.cservice.web.security.lib.TokenService;
import com.server.common.domain.exception.RAE;
import com.server.common.domain.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenService tokenService;
    private final ClientService clientService;
    private final JwtProperties jwtProperties;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        Client client = clientService.getByUsername(request.getUsername());
        LoginResponseDto response = new LoginResponseDto();
        response.setId(client.getId());
        response.setUsername(client.getUsername());

        response.setAccess(
                tokenService.create(
                        TokenParameters.builder(
                                        request.getUsername(),
                                        TokenType.ACCESS.name(),
                                        jwtProperties.getAccess()
                                )
                                .build()
                )
        );
        response.setRefresh(
                tokenService.create(
                        TokenParameters.builder(
                                        request.getUsername(),
                                        TokenType.REFRESH.name(),
                                        jwtProperties.getRefresh()
                                )
                                .build()
                )
        );

        return response;
    }

    @Override
    public void register(Client client) {
        if (clientService.existsByUsername(client.getUsername())) {
            throw new RAE();
        }
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientService.create(client);
    }
}
