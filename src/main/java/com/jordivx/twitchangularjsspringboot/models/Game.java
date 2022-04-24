package com.jordivx.twitchangularjsspringboot.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Game {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("box_art_url")
    private String boxArtUrl;
}
