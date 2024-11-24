package com.my.spotify.client.spotify;

import com.my.spotify.client.spotify.dto.GenreListRespDto;
import com.my.spotify.client.spotify.dto.SearchRespDto;
import com.my.spotify.client.spotify.dto.TokenRespDto;
import com.my.spotify.client.spotify.ex.ErrorCode;
import com.my.spotify.client.spotify.ex.SpotifyApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpotifyClient {

    private final SpotifyApi spotifyApi;
    private final SpotifyTokenManager spotifyTokenManager;
    private final String q = "genre: ";


    // 장르에 따른 트랙 리스트 검색
    public SearchRespDto searchTrack(String genre){
        return executeWithValidToken(() -> {
            Paging<Track> trackPage = spotifyApi.searchTracks(q + genre)
                    .build()
                    .execute();
            return new SearchRespDto(trackPage);
        }, String.format("장르 '%s' 기반 트랙 조회 중 에러 발생", genre));
    }

    //spotify에서 제공하는 장르 목록 조회
    public GenreListRespDto getAvailableGenres(){
        return executeWithValidToken(() -> {
            String[] genreList = spotifyApi.getAvailableGenreSeeds().build().execute();
            return new GenreListRespDto(genreList);
        }, "장르 목록 조회 중 에러 발생");
    }


    private <T> T executeWithValidToken(SpotifyApiCall<T> apiCall, String errorMsg){
        try {
            spotifyApi.setAccessToken(spotifyTokenManager.getValidToken());
            return apiCall.execute();
        } catch (Exception e){
            log.error(errorMsg);
            throw new SpotifyApiException(e);
        }
    }
}
