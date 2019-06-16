package com.qedplus.particles.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.qedplus.particles.db.ConfirmInteraction;
import com.qedplus.particles.db.Diagram;

@Dao
public interface DiagramDao {
    @Query("Select * from Diagram where diagramId LIKE :diagramId")
    Diagram findById(long diagramId);

    @Insert
    long[] insertAll(Diagram... diagrams);

    @Delete
    void delete(Diagram diagram);

    @Update
    void updateAll(Diagram... diagrams);
}
