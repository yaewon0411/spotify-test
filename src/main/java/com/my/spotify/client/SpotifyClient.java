package com.my.spotify.client;

import com.my.spotify.client.dto.GenreListRespDto;
import com.my.spotify.client.dto.SearchRespDto;
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
    private final String q = "genre: ";

    // 장르에 따른 트랙 리스트 검색
    public SearchRespDto searchTrack(String genre){
        try {
            Paging<Track> trackPage = spotifyApi.searchTracks(q + genre)
                    .build()
                    .execute();
            return new SearchRespDto(trackPage);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            log.error("장르 기반 트랙 조회 중 에러 발생: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    //액세스 토큰 발급
    public void getAccessToken() {
        try {
            ClientCredentials execute = spotifyApi.clientCredentials()
                    .build()
                    .execute();
            System.out.println("execute.getAccessToken() = " + execute.getAccessToken());
            spotifyApi.setAccessToken(execute.getAccessToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            log.error("토큰 발급 중 에러 발생: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    //spotify에서 제공하는 장르 목록 조회
    public GenreListRespDto getAvailableGenres(){
        try {
            String[] genreList = spotifyApi.getAvailableGenreSeeds().build().execute();
            return new GenreListRespDto(genreList);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
