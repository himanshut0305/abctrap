package com.qedplus.particles.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private long userId;

    @NonNull
    private String username;

    private long schoolId;

    @NonNull
    private String accessToken;

    private String refreshToken;

    private String name;
    private String rollNo;
    private String admissionNo;

    private long schoolClassId;

    public User() {}

    @Ignore
    public User(@NonNull String username, long schoolId, @NonNull String accessToken) {
        this.username = username;
        this.schoolId = schoolId;
        this.accessToken = accessToken;
    }

    @Ignore
    public User(@NonNull String username, @NonNull String accessToken) {
        this.username = username;
        this.accessToken = accessToken;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(@NonNull String rollNo) {
        this.rollNo = rollNo;
    }

    @NonNull
    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(@NonNull String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public long getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(long schoolClassId) {
        this.schoolClassId = schoolClassId;
    }

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    @NonNull
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(@NonNull String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", schoolId=" + schoolId +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", name='" + name + '\'' +
                ", rollNo='" + rollNo + '\'' +
                ", admissionNo='" + admissionNo + '\'' +
                ", schoolClassId=" + schoolClassId +
                '}';
    }
}
