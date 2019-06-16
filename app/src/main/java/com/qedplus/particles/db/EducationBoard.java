package com.qedplus.particles.db;

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
public class EducationBoard {
    @PrimaryKey(autoGenerate = true)
    private int educationBoardId;

    @NonNull
    String name;

    private String aka;

    public EducationBoard(@NonNull String name, String aka) {
        this.name = name;
        this.aka = aka;
    }

    public int getEducationBoardId() {
        return educationBoardId;
    }

    public void setEducationBoardId(int educationBoardId) {
        this.educationBoardId = educationBoardId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getAka() {
        return aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    @Override
    public String toString() {
        return "EducationBoard{" +
                "educationBoardId=" + educationBoardId +
                ", name='" + name + '\'' +
                ", aka='" + aka + '\'' +
                '}';
    }
}
