package com.qedplus.particles.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qedplus.particles.db.Chapter;
import com.qedplus.particles.db.Subject;

import java.util.List;

@Dao
public interface ChapterDao {
    @Query("SELECT * FROM Chapter where subjectId LIKE :subjectId")
    List<Chapter> findAllBySubject (long subjectId);

    @Query("SELECT * FROM Chapter where chapterIndex LIKE :chapterIndex AND subjectId LIKE :subjectId")
    Chapter getByIndexAndSubject (int chapterIndex, long subjectId);

    @Query("SELECT * FROM Chapter where name LIKE :name AND subjectId LIKE :subjectId")
    Chapter getByNameAndSubject (String name, long subjectId);

    @Insert
    long[] insertAll(Chapter... chapters);

    @Delete
    void delete(Chapter chapter);
}
