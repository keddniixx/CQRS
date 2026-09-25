package com.server.cservice.service.auth;

import com.server.cservice.web.dto.LoginRequestDto;
import com.server.cservice.web.dto.LoginResponseDto;
import com.server.common.domain.model.Client;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto request);
    void register(Client client);
}
