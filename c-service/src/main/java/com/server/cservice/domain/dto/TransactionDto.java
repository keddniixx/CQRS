package com.server.cservice.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionDto {
    @NotNull
    private CardDto from;
    @NotNull
    private CardDto to;
    @NotNull
    @Positive
    private BigDecimal amount;
}
