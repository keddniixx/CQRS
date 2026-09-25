package com.server.cservice.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LoginResponseDto {

    private UUID id;
    private String username;
    private String access;
    private String refresh;

}
