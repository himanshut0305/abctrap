package com.qedplus.particlesTeacher.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qedplus.particlesTeacher.db.School;
import com.qedplus.particlesTeacher.db.Subject;

import java.util.List;

@Dao
public interface SchoolDao {
    @Query("SELECT * FROM School where schoolId LIKE :schoolId")
    School findById(long schoolId);

    @Query("SELECT * FROM School")
    List<School> getAll();

    @Query("SELECT * FROM School where name LIKE  :name")
    School findByName(String name);

    @Query("SELECT * FROM School where code LIKE  :code")
    School findByCode(String code);

    @Query("SELECT COUNT(*) from School")
    int countSchools();

    @Insert
    long[] insertAll(School... schools);

    @Delete
    void delete(School school);
}
