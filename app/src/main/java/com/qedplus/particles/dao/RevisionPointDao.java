package com.qedplus.particles.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qedplus.particles.db.RevisionPoint;
import com.qedplus.particles.db.RevisionSlide;

import java.util.List;

@Dao
public interface RevisionPointDao {
    @Query("Select * from RevisionPoint where revisionPointId LIKE :revisionPointId")
    RevisionPoint findById(long revisionPointId);

    @Insert
    long[] insertAll(RevisionPoint... revisionPoints);

    @Delete
    void delete(RevisionPoint revisionPoint);
}
