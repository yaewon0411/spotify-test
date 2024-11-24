package com.my.spotify.client.spotify.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final String code;
    private final String message;
    private final String detail;
}