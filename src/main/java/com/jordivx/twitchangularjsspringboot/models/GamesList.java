package com.jordivx.twitchangularjsspringboot.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GamesList {
    @JsonProperty("data")
    private List<Game> data;

    @JsonProperty("pagination")
    private Object pagination;
}
