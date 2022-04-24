package com.jordivx.twitchangularjsspringboot.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class StreamsList {
    @JsonProperty("data")
    private List<Stream> data;

    @JsonProperty("pagination")
    private Object pagination;
}
