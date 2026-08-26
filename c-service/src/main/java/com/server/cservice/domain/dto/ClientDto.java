package com.server.cservice.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {
    @NotBlank
    private String name;
    
    @Email
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
}
