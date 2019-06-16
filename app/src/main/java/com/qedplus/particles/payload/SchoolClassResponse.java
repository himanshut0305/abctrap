package com.qedplus.particles.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SchoolClassResponse {
    @JsonProperty("name") private String name;
    @JsonProperty("section") private String section;
    @JsonProperty("schoolYear") private SchoolYearResponse schoolYear;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public SchoolYearResponse getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYearResponse schoolYear) {
        this.schoolYear = schoolYear;
    }

    @Override
    public String toString() {
        return "SchoolClassResponse{" +
                "name='" + name + '\'' +
                ", section='" + section + '\'' +
                ", schoolYear='" + schoolYear + '\'' +
                '}';
    }
}
