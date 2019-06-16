package com.qedplus.particles.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QPTResponse {
    @JsonProperty("name") private String name;
    @JsonProperty("qptIndex") private int qptIndex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQptIndex() {
        return qptIndex;
    }

    public void setQptIndex(int qptIndex) {
        this.qptIndex = qptIndex;
    }

    @Override
    public String toString() {
        return "QPTResponse{" +
                "name='" + name + '\'' +
                ", qptIndex=" + qptIndex +
                '}';
    }
}
