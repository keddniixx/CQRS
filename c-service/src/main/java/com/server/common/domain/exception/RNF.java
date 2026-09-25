package com.server.common.domain.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RNF extends RuntimeException {
    public RNF(String message) {
        super(message);
    }
}
