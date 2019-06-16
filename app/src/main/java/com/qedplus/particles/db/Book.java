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
                "name",
                "schoolYear"
            },
            unique = true
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
public class Book {
    @PrimaryKey(autoGenerate = true)
    private int bookId;

    private String name;
    private long subjectId;
    private int schoolYear;
    private int version;

    public Book() { }
    public Book(@NonNull String name, long subjectId, int schoolYear, int version) {
        this.name = name;
        this.subjectId = subjectId;
        this.schoolYear = schoolYear;
        this.version = version;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", subjectId=" + subjectId +
                ", schoolYear=" + schoolYear +
                ", version=" + version +
                '}';
    }
}
