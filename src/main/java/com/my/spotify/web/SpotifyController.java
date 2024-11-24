package com.my.spotify.web;

import com.my.spotify.client.spotify.SpotifyClient;
import com.my.spotify.client.spotify.dto.GenreListRespDto;
import com.my.spotify.client.spotify.dto.SearchRespDto;
import com.my.spotify.client.spotify.dto.TokenRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SpotifyController {

    private final SpotifyClient spotifyClient;


    @GetMapping("/spotify/search/tracks")
    public ResponseEntity<SearchRespDto> getTrackListByGenre(@RequestParam(value = "genre") String genre){
        return new ResponseEntity<>(spotifyClient.searchTrack(genre), HttpStatus.OK);
    }

    @GetMapping("/spotify/genres")
    public ResponseEntity<GenreListRespDto> getAvailableGenres(){
        return new ResponseEntity<>(spotifyClient.getAvailableGenres(), HttpStatus.OK);
    }
}
