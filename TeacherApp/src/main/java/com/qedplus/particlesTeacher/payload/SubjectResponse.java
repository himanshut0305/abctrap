package com.qedplus.particlesTeacher.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectResponse {
    @JsonProperty("name") public String name;
    @JsonProperty("subjectGroup") public SubjectGroupResponse subjectGroup;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubjectGroupResponse getSubjectGroup() {
        return subjectGroup;
    }

    public void setSubjectGroup(SubjectGroupResponse subjectGroup) {
        this.subjectGroup = subjectGroup;
    }

    @Override
    public String toString() {
        return "SubjectResponse{" +
                "name='" + name + '\'' +
                ", subjectGroup=" + subjectGroup +
                '}';
    }
}
