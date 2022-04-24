package com.jordivx.twitchangularjsspringboot.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Channel {
    @JsonProperty("broadcaster_language")
    private String broadcasterLanguage;
    @JsonProperty("broadcaster_login")
    private String broadcasterLogin;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("game_id")
    private String gameId;
    @JsonProperty("game_name")
    private String gameName;
    @JsonProperty("id")
    private String id;
    @JsonProperty("is_live")
    private Boolean isLive;
    @JsonProperty("tags_ids")
    private List<String> tagsIds;
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;
    @JsonProperty("title")
    private String title;
    @JsonProperty("started_at")
    private String startedAt;

}
