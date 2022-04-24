package com.jordivx.twitchangularjsspringboot.controllers;

import com.jordivx.twitchangularjsspringboot.models.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Data
@RestController
public class TwitchController {
    @Value("${twitchApi.baseUrl:https://api.twitch.tv/helix}")
    private String twitchApiBaseUrl;
    @Value("${twitchApi.clientId:xxx}")
    private String twitchApiClientId;
    @Value("${twitchApi.secret:xxx}")
    private String twitchApiSecret;

    WebClient client;

    public TwitchController() {
        this.client = WebClient.create();
    }

    String logIn() {

        /*
        ToDo: Instead of calling logIn every time, I would define a client property to store the token value
        and use it in the header every time. Then check the client calls onError with status 401-403 and
        call again the endpoint. Don't have the time to do it properly.
        Also I'm assuming the token type is bearer every time, we could use the property type to define the header
        on further calls.
        */

        WebClient loginClient = WebClient.create();

        return loginClient
                .post()
                .uri(UriComponentsBuilder
                        .fromHttpUrl("https://id.twitch.tv")
                        .path("/oauth2/token")
                        .build()
                        .toUri())
                .body(BodyInserters.fromFormData("client_id", this.getTwitchApiClientId())
                        .with("client_secret", this.getTwitchApiSecret())
                        .with("grant_type", "client_credentials"))
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .onErrorMap(e -> new Exception("Error",e))
                .map(res -> res.getAccessToken())
                .block();
    }

    @GetMapping("/search-channels")
    public List<Channel> searchChannels(@RequestParam(required = true) String query) {
        String token = logIn();

        return client.get()
            .uri(UriComponentsBuilder
                    .fromHttpUrl(this.getTwitchApiBaseUrl())
                    .path("/search/channels")
                    .queryParam("query", query)
                    .build()
                    .toUri()
            )
            .header(HttpHeaders.AUTHORIZATION, "Bearer "+token)
            .header("Client-Id", this.twitchApiClientId)
            .retrieve()
            .bodyToMono(ChannelsList.class)
            .onErrorMap(e -> new Exception("Error",e))
            .map(res -> res.getData())
            .block();
    }

    @GetMapping("/streams")
    public List<Stream> getStreams(@RequestParam(required = false) String userId, @RequestParam(required = false) String gameId) {
        String token = logIn();
        return client.get()
                .uri(UriComponentsBuilder
                        .fromHttpUrl(this.getTwitchApiBaseUrl())
                        .path("/streams")
                        .queryParamIfPresent("user_id", Optional.ofNullable(userId))
                        .queryParamIfPresent("game_id", Optional.ofNullable(gameId))
                        .build()
                        .toUri()
                )
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+token)
                .header("Client-Id", this.twitchApiClientId)
                .retrieve()
                .bodyToMono(StreamsList.class)
                .onErrorMap(e -> new Exception("Error",e))
                .map(res -> res.getData())
                .block();
    }

    @GetMapping("/top-games")
    public List<Game> getTopGames() {
        String token = logIn();
        return client.get()
                .uri(UriComponentsBuilder
                        .fromHttpUrl(this.getTwitchApiBaseUrl())
                        .path("/games/top")
                        .build()
                        .toUri()
                )
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+token)
                .header("Client-Id", this.twitchApiClientId)
                .retrieve()
                .bodyToMono(GamesList.class)
                .onErrorMap(e -> new Exception("Error",e))
                .map(res -> res.getData())
                .block();
    }
}
