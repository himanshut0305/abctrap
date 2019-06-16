package com.qedplus.particles.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.qedplus.particles.db.ConfirmInteraction;
import com.qedplus.particles.db.RevealInteraction;

@Dao
public interface ConfirmInteractionDao {
    @Query("Select * from ConfirmInteraction where confirmInteractionId LIKE :confirmInteractionId")
    ConfirmInteraction findById(long confirmInteractionId);

    @Insert
    long[] insertAll(ConfirmInteraction... confirmInteractions);

    @Delete
    void delete(ConfirmInteraction confirmInteraction);

    @Update
    void updateAll(ConfirmInteraction... confirmInteractions);
}
