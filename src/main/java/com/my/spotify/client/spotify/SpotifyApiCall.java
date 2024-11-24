package com.my.spotify.client.spotify;

import com.my.spotify.client.spotify.ex.SpotifyApiException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import org.apache.hc.core5.http.ParseException;

//스포티파이 api 호출 관리
@FunctionalInterface
public interface SpotifyApiCall<T>{
    T execute() throws IOException, ParseException, SpotifyWebApiException;
}
