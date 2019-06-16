package com.qedplus.particlesTeacher.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RevisionPointResponse {
    @JsonProperty("preface") private String preface;
    @JsonProperty("explanation") private String explanation;

    public String getPreface() {
        return preface;
    }

    public void setPreface(String preface) {
        this.preface = preface;
    }

    public String getExplanation() {
        return explanation
                .replace("<formula>", "\\( ")
                .replace("</formula>", " \\)")
                .replace("cfrac", "frac")
                .replace("\\implies", "\\Rightarrow");
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public String toString() {
        return "RevisionPointResponse{" +
                "preface='" + preface + '\'' +
                ", explanation='" + explanation + '\'' +
                '}';
    }
}
