package com.qedplus.particles.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDetailResponse {
    @JsonProperty("rollNo") private String rollNo;
    @JsonProperty("admissionNo") private String admissionNo;
    @JsonProperty("schoolClass") private SchoolClassResponse schoolClass;
    @JsonProperty("user") private UserResponse user;

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public SchoolClassResponse getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClassResponse schoolClass) {
        this.schoolClass = schoolClass;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "StudentDetailResponse{" +
                "rollNo='" + rollNo + '\'' +
                ", admissionNo='" + admissionNo + '\'' +
                ", schoolClass=" + schoolClass +
                ", user=" + user +
                '}';
    }
}
