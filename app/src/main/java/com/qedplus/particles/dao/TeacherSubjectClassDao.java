package com.qedplus.particles.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qedplus.particles.db.TeacherSubjectClass;

import java.util.List;

@Dao
public interface TeacherSubjectClassDao {
    @Query("Select * from TeacherSubjectClass where userId LIKE :userId")
    List<TeacherSubjectClass> findAllByTeacher(long userId);

    @Insert
    long[] insertAll(TeacherSubjectClass... teacherSubjectClasses);

    @Delete
    void delete(TeacherSubjectClass teacherSubjectClass);
}
