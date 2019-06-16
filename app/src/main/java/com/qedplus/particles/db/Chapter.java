package com.qedplus.particles.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

@Entity(
    indices = {
        @Index(
            value = {
                "name",
                "subjectId"
            },
            unique = true
        ),
        @Index(
            value = {
                "chapterIndex",
                "subjectId"
            },
            unique = true
        ),
        @Index(
            value = {
                "subjectId"
            }
        )
    },
    foreignKeys = {
        @ForeignKey(
            entity = Subject.class,
            parentColumns = "subjectId",
            childColumns = "subjectId"
        )
    }
)
public class Chapter {
    @PrimaryKey(autoGenerate = true)
    private int chapterId;

    @NonNull
    private String name;

    private int chapterIndex;
    private long subjectId;
    private int version;

    @NonNull private Date updatedOn;

    public Chapter(@NonNull String name, int chapterIndex, long subjectId, int version) {
        this.name = name;
        this.chapterIndex = chapterIndex;
        this.subjectId = subjectId;
        this.version = version;

        this.updatedOn = Calendar.getInstance().getTime();
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getChapterIndex() {
        return chapterIndex;
    }

    public void setChapterIndex(int chapterIndex) {
        this.chapterIndex = chapterIndex;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    @NonNull
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(@NonNull Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "chapterId=" + chapterId +
                ", name='" + name + '\'' +
                ", chapterIndex=" + chapterIndex +
                ", subjectId=" + subjectId +
                ", version=" + version +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
