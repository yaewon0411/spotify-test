package com.my.spotify.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class GenreListRespDto {

    private final List<GenreRespDto> genres = new ArrayList<>();

    public GenreListRespDto(String[] genreList) {
        for (String genre : genreList) {
            genres.add(new GenreRespDto(genre));
        }
    }

    @NoArgsConstructor
    @Getter
    public static class GenreRespDto{
        private String genre;

        public GenreRespDto(String genre) {
            this.genre = genre;
        }
    }

}
