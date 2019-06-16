package com.qedplus.particles.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.qedplus.particles.db.Announcement;

import java.util.List;

@Dao
public interface AnnouncementDao {
    @Query("SELECT * FROM Announcement")
    List<Announcement> getAllAnnouncements();

    @Query("SELECT * FROM Announcement where seen LIKE 0")
    List<Announcement> getAllUnseenAnnouncements();

    @Insert
    void insertAll(Announcement... announcements);

    @Update
    int updateAll(Announcement... announcements);

    @Delete
    void delete(Announcement announcement);

    @Query("UPDATE Announcement SET seen = 1 WHERE madeOn = (SELECT MAX(madeOn) FROM Announcement WHERE seen = 0)")
    void setLatestAsSeen();
}
