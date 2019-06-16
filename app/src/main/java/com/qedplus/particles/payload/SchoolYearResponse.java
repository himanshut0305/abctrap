package com.qedplus.particles.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SchoolYearResponse {
    @JsonProperty("id") private String name;
    @JsonProperty("school") private SchoolResponse school;
    @JsonProperty("year") private YearResponse year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SchoolResponse getSchool() {
        return school;
    }

    public void setSchool(SchoolResponse school) {
        this.school = school;
    }

    public YearResponse getYear() {
        return year;
    }

    public void setYear(YearResponse year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "SchoolYearResponse{" +
                "name='" + name + '\'' +
                ", school=" + school +
                ", year=" + year +
                '}';
    }
}
