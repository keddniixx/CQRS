package com.server.cservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDto {
    @NotBlank
    private String number;
    @NotBlank
    private String date;
    @NotBlank
    private String cvv;
}
