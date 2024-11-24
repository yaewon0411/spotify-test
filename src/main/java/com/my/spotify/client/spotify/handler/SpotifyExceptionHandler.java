package com.my.spotify.client.spotify.handler;

import com.my.spotify.client.spotify.ex.ErrorCode;
import com.my.spotify.client.spotify.ex.SpotifyApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class SpotifyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e){
        log.error("예기치 못한 내부 오류 발생: {}", e.getMessage(), e);
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        String detailMessage = e.getCause() != null ? e.getCause().getMessage() : null;

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(new ErrorResponse(
                        errorCode.getCode(),
                        errorCode.getMessage(),
                        detailMessage
                ));
    }

    @ExceptionHandler(SpotifyApiException.class)
    public ResponseEntity<ErrorResponse> handleSpotifyApiException(SpotifyApiException e) {
        ErrorCode errorCode = e.getErrorCode();
        String detailMessage = e.getCause() != null ? e.getCause().getMessage() : null;

        log.error("[{}] Spotify 오류 발생: {}", errorCode.getCode(), e.getMessage(), e);

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(new ErrorResponse(
                        errorCode.getCode(),
                        errorCode.getMessage(),
                        detailMessage
                ));
    }

}
