package com.qedplus.particlesTeacher.db;

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
            value = {
                "name",
                "schoolYear",
                "schoolId"
            },
            unique = true
        )
    },
    foreignKeys = {
        @ForeignKey(
            entity = School.class,
            parentColumns = "schoolId",
            childColumns = "schoolId"
        )
    }
)
public class SchoolClass {
    @Ignore public static final int SIX = 6;
    @Ignore public static final int SEVEN = 7;
    @Ignore public static final int EIGHT = 8;

    @Ignore public static final int NINE = 9;
    @Ignore public static final int TEN = 10;
    @Ignore public static final int ELEVEN = 11;

    @Ignore public static final int TWELVE = 12;

    @PrimaryKey(autoGenerate = true)
    private int schoolClassId;

    @NonNull String name;
    private int schoolYear;
    private long schoolId;
    @NonNull private String section;

    @StringDef({"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"})
    @Retention(RetentionPolicy.SOURCE)
    @interface ClassSection { }

    @IntDef({SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN, TWELVE})
    @Retention(RetentionPolicy.SOURCE)
    @interface SchoolYear { }

    public SchoolClass(@SchoolYear int schoolYear, @NonNull @ClassSection String section, long schoolId) {
        this.name = "" + schoolYear + section;
        this.schoolYear = schoolYear;
        this.section = section;
        this.schoolId = schoolId;
    }

    public int getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(int schoolClassId) {
        this.schoolClassId = schoolClassId;
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

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    @NonNull
    public String getSection() {
        return section;
    }

    public void setSection(@NonNull String section) {
        this.section = section;
    }
}