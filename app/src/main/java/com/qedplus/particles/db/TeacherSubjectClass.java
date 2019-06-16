package com.qedplus.particles.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
    indices = {
        @Index(
            value = {
                "userId",
                "subjectId",
                "schoolClassId"
            },
            unique = true
        )
    },
    foreignKeys = {
        @ForeignKey(
            entity = User.class,
            parentColumns = "userId",
            childColumns = "userId"
        ),
        @ForeignKey(
            entity = Subject.class,
            parentColumns = "subjectId",
            childColumns = "subjectId"
        ),
        @ForeignKey(
            entity = SchoolClass.class,
            parentColumns = "schoolClassId",
            childColumns = "schoolClassId"
        )
    }
)
public class TeacherSubjectClass {
    @PrimaryKey(autoGenerate = true)
    private long teacherSubjectClassId;

    private long userId;
    private long subjectId;
    private long schoolClassId;

    public TeacherSubjectClass(long userId, long subjectId, long schoolClassId) {
        this.userId = userId;
        this.subjectId = subjectId;
        this.schoolClassId = schoolClassId;
    }

    public long getTeacherSubjectClassId() {
        return teacherSubjectClassId;
    }

    public void setTeacherSubjectClassId(long teacherSubjectClassId) {
        this.teacherSubjectClassId = teacherSubjectClassId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public long getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(int schoolClassId) {
        this.schoolClassId = schoolClassId;
    }

    @Override
    public String toString() {
        return "TeacherSubjectClass{" +
                "teacherSubjectClassId=" + teacherSubjectClassId +
                ", userId=" + userId +
                ", subjectId=" + subjectId +
                ", schoolClassId=" + schoolClassId +
                '}';
    }
}
