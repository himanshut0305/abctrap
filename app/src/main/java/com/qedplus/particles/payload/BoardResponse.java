package com.qedplus.particles.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardResponse {
    @JsonProperty("name") private String name;
    @JsonProperty("aka") private String aka;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAka() {
        return aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    @Override
    public String toString() {
        return "BoardResponse{" +
                "name='" + name + '\'' +
                ", aka='" + aka + '\'' +
                '}';
    }
}