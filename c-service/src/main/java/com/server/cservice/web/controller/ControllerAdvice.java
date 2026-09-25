package com.server.cservice.web.controller;

import com.server.common.domain.exception.RAE;
import com.server.common.domain.exception.RNF;
import com.server.cservice.web.dto.MessageDto;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice

public class ControllerAdvice {

    @ExceptionHandler(RNF.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageDto resourceNotFound(
            final RNF e
            ){
        return new MessageDto(e.getMessage() != null
                ? e.getMessage()
                : "Не найдено"
                );
    }

    @ExceptionHandler(RAE.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDto resourceNotFound(
            final RAE e
    ){
        return new MessageDto(e.getMessage() != null
                ? e.getMessage()
                : "Уже существует"
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDto illegalState(
            final IllegalStateException e
    ){
        return new MessageDto(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MessageDto exception(
            final Exception e
    ){
        e.printStackTrace();
        return new MessageDto("Ошибка сервера");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDto validation(
            final MethodArgumentNotValidException e
    ){
        Map<String, String> errors = e.getBindingResult()
                .getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage,
                        (existingResult,newMessage) -> existingResult + " " +newMessage
                ));
        return new MessageDto("Валидация провалена",errors);
    }


}
