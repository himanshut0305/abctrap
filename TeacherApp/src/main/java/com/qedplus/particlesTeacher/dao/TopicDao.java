package com.qedplus.particlesTeacher.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qedplus.particlesTeacher.db.Topic;

import java.util.List;

@Dao
public interface TopicDao {
    @Query("Select * from Topic where chapterId LIKE :chapterId")
    List<Topic> findAllByChapter(int chapterId);

    @Query("Select * from Topic where topicIndex LIKE :topicIndex and chapterId LIKE :chapterId")
    Topic getByChapterAndIndex(int topicIndex, int chapterId);

    @Insert
    long[] insertAll(Topic... topics);

    @Delete
    void delete(Topic topic);
}
