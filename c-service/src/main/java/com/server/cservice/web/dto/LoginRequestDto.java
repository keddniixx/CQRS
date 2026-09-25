package com.server.cservice.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    @NotNull(
            message = "Юзернейм не налл"
    )
    private String username;

    @NotNull(
            message = "Пассворд не налл"
    )
    private String password;
}
