package com.qedplus.particlesTeacher.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopicResponse {
    @JsonProperty("name") private String name;
    @JsonProperty("topicIndex") private int topicIndex;
    @JsonProperty("description") private String description;


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


    @Override
    public String toString() {
        return "TopicResponse{" +
                "name='" + name + '\'' +
                ", topicIndex=" + topicIndex +
                ", description='" + description + '\'' +
                '}';
    }
}
