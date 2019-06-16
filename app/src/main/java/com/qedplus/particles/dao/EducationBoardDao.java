package com.qedplus.particles.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qedplus.particles.db.EducationBoard;

@Dao
public interface EducationBoardDao {
    @Query("SELECT * FROM EducationBoard where name LIKE  :name")
    EducationBoard findByName(String name);

    @Query("SELECT * FROM EducationBoard where aka LIKE  :aka")
    EducationBoard findByAka(String aka);

    @Insert
    long[] insertAll(EducationBoard... educationBoards);

    @Delete
    void delete(EducationBoard educationBoard);
}
