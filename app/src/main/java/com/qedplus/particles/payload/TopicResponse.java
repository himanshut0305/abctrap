package com.qedplus.particles.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopicResponse {
    @JsonProperty("name") private String name;
    @JsonProperty("topicIndex") private int topicIndex;
    @JsonProperty("description") private String description;
    @JsonProperty("qpt") private QPTResponse qpt;
    @JsonProperty("doesPrecedeQPT") private boolean doesPrecedeQPT;
    @JsonProperty("version") private int version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTopicIndex() {
        return topicIndex;
    }

    public void setTopicIndex(int topicIndex) {
        this.topicIndex = topicIndex;
    }

    public String getDescription() {
        if(this.description == null)
            return "";

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QPTResponse getQpt() {
        return qpt;
    }

    public void setQpt(QPTResponse qpt) {
        this.qpt = qpt;
    }

    public boolean isDoesPrecedeQPT() {
        return doesPrecedeQPT;
    }

    public void setDoesPrecedeQPT(boolean doesPrecedeQPT) {
        this.doesPrecedeQPT = doesPrecedeQPT;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "TopicResponse{" +
                "name='" + name + '\'' +
                ", topicIndex=" + topicIndex +
                ", description='" + description + '\'' +
                ", qpt=" + qpt +
                ", doesPrecedeQPT=" + doesPrecedeQPT +
                ", version=" + version +
                '}';
    }
}
