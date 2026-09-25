package com.server.common.domain.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RAE extends RuntimeException {
    public RAE(String message) {
        super(message);
    }
}
