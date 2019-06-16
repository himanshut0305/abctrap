package com.qedplus.particlesTeacher.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeacherDetailResponse {
    @JsonProperty("emailId") private String emailId;
    @JsonProperty("phoneNo") private String phoneNo;
    @JsonProperty("user") private UserResponse userResponse;
    @JsonProperty("school") private SchoolResponse schoolResponse;
    @JsonProperty("schoolClassSubjects") private SchoolClassSubjectsResponse[] schoolClassSubjects;

    public String getEmailId() {
        return emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public SchoolClassSubjectsResponse[] getSchoolClassSubjects() {
        return schoolClassSubjects;
    }

    public void setSchoolClassSubjects(SchoolClassSubjectsResponse[] schoolClassSubjects) {
        this.schoolClassSubjects = schoolClassSubjects;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public SchoolResponse getSchoolResponse() {
        return schoolResponse;
    }

    public void setSchoolResponse(SchoolResponse schoolResponse) {
        this.schoolResponse = schoolResponse;
    }

    @Override
    public String toString() {
        return "TeacherDetailResponse{" +
                "emailId='" + emailId + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", userResponse=" + userResponse +
                ", schoolResponse=" + schoolResponse +
                ", schoolClassSubjects=" + Arrays.toString(schoolClassSubjects) +
                '}';
    }
}
