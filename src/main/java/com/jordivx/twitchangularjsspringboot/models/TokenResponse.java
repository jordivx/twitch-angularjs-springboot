package com.jordivx.twitchangularjsspringboot.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigInteger;

@Data
public class TokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private BigInteger expiresIn;

    @JsonProperty("token_type")
    private String tokenType;
}
