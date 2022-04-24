package com.jordivx.twitchangularjsspringboot.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChannelsList {
    @JsonProperty("data")
    private List<Channel> data;

    @JsonProperty("pagination")
    private Object pagination;
}
