package com.qedplus.particles.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Entity(
    indices = {
        @Index(
            value = "name",
            unique = true
        ),
        @Index(
            value = "code",
            unique = true
        )
    },
    foreignKeys = {
        @ForeignKey(
            entity = EducationBoard.class,
            parentColumns = "educationBoardId",
            childColumns = "educationBoardId"
        )
    }
)
public class School {
    @PrimaryKey(autoGenerate = true) private int schoolId;
    @NonNull private String name;
    @NonNull private String code;
    private int educationBoardId;
    @NonNull private String logoURI;
    @NonNull private String schoolTheme;


    @StringDef({"RED", "BLUE", "GREEN", "INDIGO", "ORANGE", "PURPLE", "TEAL", "BROWN"})
    @Retention(RetentionPolicy.SOURCE)
    @interface SchoolTheme { }

    public School(@NonNull String name, @NonNull String code, int educationBoardId, @NonNull String logoURI, @NonNull @SchoolTheme String schoolTheme) {
        this.name = name;
        this.code = code;
        this.educationBoardId = educationBoardId;
        this.logoURI = logoURI;
        this.schoolTheme = schoolTheme;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    public void setCode(@NonNull String code) {
        this.code = code;
    }

    public int getEducationBoardId() {
        return educationBoardId;
    }

    public void setEducationBoardId(int educationBoardId) {
        this.educationBoardId = educationBoardId;
    }

    @NonNull
    public String getLogoURI() {
        return logoURI;
    }

    public void setLogoURI(@NonNull String logoURI) {
        this.logoURI = logoURI;
    }

    @NonNull
    public String getSchoolTheme() {
        return schoolTheme;
    }

    public void setSchoolTheme(@NonNull String schoolTheme) {
        this.schoolTheme = schoolTheme;
    }

    @Override
    public String toString() {
        return "SchoolResponse{" +
                "schoolId=" + schoolId +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", educationBoardId=" + educationBoardId +
                ", logoURI='" + logoURI + '\'' +
                ", schoolTheme='" + schoolTheme + '\'' +
                '}';
    }
}