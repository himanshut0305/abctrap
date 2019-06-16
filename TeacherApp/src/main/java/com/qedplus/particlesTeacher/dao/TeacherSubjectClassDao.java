package com.qedplus.particlesTeacher.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qedplus.particlesTeacher.db.TeacherSubjectClass;

import java.util.List;

@Dao
public interface TeacherSubjectClassDao {


    @Query("Select * from TeacherSubjectClass ")
    List<TeacherSubjectClass> findAll();

    @Query("Select * from TeacherSubjectClass where userId LIKE :userId")
    List<TeacherSubjectClass> findAllByTeacher(long userId);

    @Query("Select * from TeacherSubjectClass where userId LIKE :userId AND subjectId LIKE :subjectId AND schoolClassId LIKE :schoolClassId")
    TeacherSubjectClass findByTeacherSubjectSchoolClass(long userId, long subjectId, long schoolClassId);

    @Insert
    long[] insertAll(TeacherSubjectClass... teacherSubjectClasses);

    @Delete
    void delete(TeacherSubjectClass teacherSubjectClass);
}
