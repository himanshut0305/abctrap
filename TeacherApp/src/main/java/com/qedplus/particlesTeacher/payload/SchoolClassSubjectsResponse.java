package com.qedplus.particlesTeacher.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SchoolClassSubjectsResponse {
    @JsonProperty("id") private String id;
    @JsonProperty("schoolClass") private SchoolClassResponse schoolClassResponse;
    @JsonProperty("subject") private SubjectResponse subjectResponse;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SchoolClassResponse getSchoolClassResponse() {
        return schoolClassResponse;
    }

    public void setSchoolClassResponse(SchoolClassResponse schoolClassResponse) {
        this.schoolClassResponse = schoolClassResponse;
    }

    public SubjectResponse getSubjectResponse() {
        return subjectResponse;
    }

    public void setSubjectResponse(SubjectResponse subjectResponse) {
        this.subjectResponse = subjectResponse;
    }

    @Override
    public String toString() {
        return "SchoolClassSubjectsResponse{" +
                "id='" + id + '\'' +
                ", schoolClassResponse=" + schoolClassResponse +
                ", subjectResponse=" + subjectResponse +
                '}';
    }
}
