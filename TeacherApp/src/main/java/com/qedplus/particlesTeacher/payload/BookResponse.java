package com.qedplus.particlesTeacher.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookResponse {
    @JsonProperty("name") public String name;
    @JsonProperty("subjectYear") private SubjectYearResponse subjectYear;
    @JsonProperty("version") private int version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubjectYearResponse getSubjectYear() {
        return subjectYear;
    }

    public void setSubjectYear(SubjectYearResponse subjectYear) {
        this.subjectYear = subjectYear;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "BookResponse{" +
                "name='" + name + '\'' +
                ", subjectYear=" + subjectYear +
                ", version=" + version +
                '}';
    }
}
