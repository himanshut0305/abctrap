package com.qedplus.particles.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qedplus.particles.db.Chapter;
import com.qedplus.particles.db.QPT;
import com.qedplus.particles.db.Topic;

import java.util.List;

@Dao
public interface QPTDao {
    @Query("SELECT * FROM QPT where chapterId LIKE :chapterId")
    List<QPT> findAllByChapter (int chapterId);

    @Query("SELECT * FROM QPT where qptIndex LIKE :qptIndex AND chapterId LIKE :chapterId")
    QPT getByIndexAndChapter (int qptIndex, int chapterId);

    @Query("SELECT * FROM Topic where qptId LIKE :qptId")
    List<Topic> getAllTopics (int qptId);

    @Insert
    long[] insertAll(QPT... qpts);

    @Delete
    void delete(QPT qpt);
}
