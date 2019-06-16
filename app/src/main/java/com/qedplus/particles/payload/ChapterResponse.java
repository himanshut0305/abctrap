package com.qedplus.particles.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChapterResponse {
    @JsonProperty("name") public String name;
    @JsonProperty("chapterIndex") public int chapterIndex;
    @JsonProperty("version") private int version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChapterIndex() {
        return chapterIndex;
    }

    public void setChapterIndex(int chapterIndex) {
        this.chapterIndex = chapterIndex;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ChapterResponse{" +
                "name='" + name + '\'' +
                ", chapterIndex=" + chapterIndex +
                ", version=" + version +
                '}';
    }
}
