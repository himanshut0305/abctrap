package com.qedplus.particles.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qedplus.particles.db.RevisionSlide;

import java.util.List;

@Dao
public interface RevisionSlideDao {
    @Query("Select * from RevisionSlide where topicId LIKE :topicId")
    List<RevisionSlide> findAllByTopic(int topicId);

    @Query("Select * from RevisionSlide where slideIndex LIKE :slideIndex and topicId LIKE :topicId")
    RevisionSlide getByTopicAndIndex(int slideIndex, int topicId);

    @Insert
    void insertAll(RevisionSlide... revisionSlides);

    @Delete
    void delete(RevisionSlide revisionSlide);
}
