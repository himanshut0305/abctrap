package com.qedplus.particlesTeacher.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qedplus.particlesTeacher.db.Book;
import com.qedplus.particlesTeacher.db.Subject;

import java.util.List;

@Dao
public interface SubjectDao {
    @Query("SELECT * FROM Subject")
    List<Subject> findAll();

    @Query("SELECT * FROM Subject where subjectId LIKE :subjectId")
    Subject findById(long subjectId);

    @Query("SELECT * FROM Subject where name LIKE :name")
    Subject findByName(String name);

    @Query("SELECT * FROM Subject where name LIKE :name AND schoolYear LIKE :schoolYear")
    Subject getByNameAndYear (String name, int schoolYear);

    @Query("SELECT * FROM Subject where schoolYear LIKE :schoolYear")
    List<Subject> findAllForSchoolYear(int schoolYear);

    @Query("SELECT * FROM Subject where schoolYear LIKE :schoolYear AND subjectGroupId LIKE :subjectGroupId")
    List<Subject> findAllBySubjectGroupForSchoolYear(int schoolYear, int subjectGroupId);

    @Insert
    long[] insertAll(Subject... subjects);

    @Delete
    void delete(Subject subject);
}
