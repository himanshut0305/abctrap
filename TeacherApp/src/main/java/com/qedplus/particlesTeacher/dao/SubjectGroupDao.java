package com.qedplus.particlesTeacher.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qedplus.particlesTeacher.db.SubjectGroup;

import java.util.List;

@Dao
public interface SubjectGroupDao {
    @Query("SELECT * FROM SubjectGroup")
    List<SubjectGroup> getAll();

    @Query("SELECT * FROM SubjectGroup where name LIKE :name")
    SubjectGroup findByName(String name);

    @Insert
    long[] insertAll(SubjectGroup... subjectGroups);

    @Delete
    void delete(SubjectGroup subjectGroup);
}
