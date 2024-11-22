package com.my.spotify.web;

import com.my.spotify.client.SpotifyClient;
import com.my.spotify.client.dto.GenreListRespDto;
import com.my.spotify.client.dto.SearchRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.specification.Track;

@RestController
@RequiredArgsConstructor
public class SpotifyController {

    private final SpotifyClient spotifyClient;

    @PostMapping("/spotify/token")
    public void getAccessToken(){
        spotifyClient.getAccessToken();
    }

    @GetMapping("/spotify/search/tracks")
    public ResponseEntity<SearchRespDto> getTrackListByGenre(@RequestParam(value = "genre") String genre){
        return new ResponseEntity<>(spotifyClient.searchTrack(genre), HttpStatus.OK);
    }

    @GetMapping("/spotify/genres")
    public ResponseEntity<GenreListRespDto> getAvailableGenres(){
        return new ResponseEntity<>(spotifyClient.getAvailableGenres(), HttpStatus.OK);
    }
}
