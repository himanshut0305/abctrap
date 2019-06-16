package com.qedplus.particles.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qedplus.particles.db.SchoolClass;

@Dao
public interface SchoolClassDao {
    @Query("SELECT * FROM SchoolClass where schoolClassId LIKE :schoolClassId")
    SchoolClass findById(final long schoolClassId);

    @Query("SELECT * FROM SchoolClass where name LIKE :name AND schoolId LIKE :schoolId")
    SchoolClass findByNameAndSchool(final String name, final int schoolId);

    @Query("SELECT * FROM SchoolClass where schoolId LIKE :schoolId")
    SchoolClass findAllBySchool(final int schoolId);

    @Query("SELECT * FROM SchoolClass where schoolId LIKE :schoolId AND schoolYear LIKE :schoolYear")
    SchoolClass findAllBySchoolYearForSchool(final int schoolId, int schoolYear);

    @Insert
    long[] insertAll(SchoolClass... schoolClasses);

    @Delete
    void delete(SchoolClass schoolClass);
}
