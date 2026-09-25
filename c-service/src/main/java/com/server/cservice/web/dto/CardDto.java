package com.server.cservice.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CardDto {

    @NotNull(message = "Айди не должно быть налл",
            groups = OnUpdate.class)
    @Null(message = "Айди должно быть налл",
            groups = OnCreate.class)

    private UUID id;

    @NotNull(message = "Номер карты не должно быть налл",
            groups = OnCreate.class)
    @Null(message = "Номер карты не должно быть налл")
    private String number;
    @NotNull(message = "Дата не должно быть налл",
            groups = OnCreate.class)
    @Null(message = "Дата не должно быть налл")
    private String date;
    @NotNull(message = "ЦВВ карты не должно быть налл",
            groups = OnCreate.class)
    @Null(message = "ЦВВ карты не должно быть налл")
    private String cvv;
}
