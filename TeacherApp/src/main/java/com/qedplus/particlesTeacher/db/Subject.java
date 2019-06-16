package com.qedplus.particlesTeacher.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
    indices = {
        @Index(
            value = {
                "name",
                "schoolYear"
            },
            unique = true
        )
    },
    foreignKeys = {
        @ForeignKey(
            entity = SubjectGroup.class,
            parentColumns = "subjectGroupId",
            childColumns = "subjectGroupId"
        )
    }
)
public class Subject {
    @PrimaryKey(autoGenerate = true)
    private int subjectId;

    @NonNull private String name;
    private long subjectGroupId;
    private int schoolYear;

    public Subject() {}
    public Subject(@NonNull String name, long subjectGroupId, int schoolYear) {
        this.name = name;
        this.subjectGroupId = subjectGroupId;
        this.schoolYear = schoolYear;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public long getSubjectGroupId() {
        return subjectGroupId;
    }

    public void setSubjectGroupId(long subjectGroupId) {
        this.subjectGroupId = subjectGroupId;
    }
}