package com.qedplus.particlesTeacher.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SigninResponse {
    @JsonProperty("accessToken") public String accessToken;
    @JsonProperty("refreshToken") public String refreshToken;
    @JsonProperty("tokenType") public String tokenType;
    @JsonProperty("expiresInMsec") public String expiresInMsec;

    @Override
    public String toString() {
        return "SigninResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresInMsec='" + expiresInMsec + '\'' +
                '}';
    }
}
