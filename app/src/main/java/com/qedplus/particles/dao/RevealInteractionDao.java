package com.qedplus.particles.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.qedplus.particles.db.RevealInteraction;
import com.qedplus.particles.db.RevisionPoint;

@Dao
public interface RevealInteractionDao {
    @Query("Select * from RevealInteraction where revealInteractionId LIKE :revealInteractionId")
    RevealInteraction findById(long revealInteractionId);

    @Insert
    long[] insertAll(RevealInteraction... revealInteractions);

    @Delete
    void delete(RevealInteraction revealInteraction);

    @Update
    void updateAll(RevealInteraction... revealInteractions);
}
