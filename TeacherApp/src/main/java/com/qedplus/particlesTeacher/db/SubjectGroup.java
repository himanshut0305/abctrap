package com.qedplus.particlesTeacher.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
    indices = {
        @Index(
            value = "name",
            unique = true
        )
    }
)
public class SubjectGroup {
    @PrimaryKey(autoGenerate = true)
    private int subjectGroupId;

    @NonNull
    String name;

    public SubjectGroup(@NonNull String name) {
        this.name = name;
    }

    public int getSubjectGroupId() {
        return subjectGroupId;
    }

    public void setSubjectGroupId(int subjectGroupId) {
        this.subjectGroupId = subjectGroupId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
}
