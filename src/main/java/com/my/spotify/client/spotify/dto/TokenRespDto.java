package com.my.spotify.client.spotify.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;

@NoArgsConstructor
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TokenRespDto {

    private String accessToken;
    private String tokenType;
    private Integer expiresIn;

    public TokenRespDto(ClientCredentials credentials) {
        this.accessToken = credentials.getAccessToken();
        this.tokenType = credentials.getTokenType();
        this.expiresIn = credentials.getExpiresIn();
    }
}
