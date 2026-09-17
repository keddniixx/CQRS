package com.server.cservice.service.auth;

import com.server.cservice.domain.dto.ClientDto;
import com.server.cservice.domain.model.Client;
import com.server.cservice.service.command.ClientCommandService;
import com.server.cservice.service.query.ClientQueryService;
import com.server.cservice.web.dto.auth.LoginRequestDto;
import com.server.cservice.web.dto.auth.LoginResponseDto;
import com.server.cservice.web.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ClientCommandService clientCommandService;
    private final ClientQueryService clientQueryService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public void register(ClientDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setUsername(clientDto.getUsername());
        client.setPassword(passwordEncoder.encode(clientDto.getPassword()));
        clientCommandService.create(client);
    }

    public LoginResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        Client client = clientQueryService.getByUsername(request.getUsername());
        String token = jwtTokenProvider.createAccessToken(request.getUsername());
        return new LoginResponseDto(client.getId(), token, token);
    }

}
