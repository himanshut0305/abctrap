package com.qedplus.particles.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectYearResponse {
    @JsonProperty("subject") public SubjectResponse subject;
    @JsonProperty("year") public YearResponse year;

    public SubjectResponse getSubject() {
        return subject;
    }

    public void setSubject(SubjectResponse subject) {
        this.subject = subject;
    }

    public YearResponse getYear() {
        return year;
    }

    public void setYear(YearResponse year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "SubjectYearResponse{" +
                "subject=" + subject +
                ", year=" + year +
                '}';
    }
}
