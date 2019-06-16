package com.qedplus.particles.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qedplus.particles.db.Badge;

import java.util.List;

@Dao
public interface BadgeDao {
    @Query("SELECT * FROM Badge")
    List<Badge> getAllBadges();


    @Query("SELECT * FROM Badge where seen LIKE 0")
    List<Badge> getAllUnseenBadges();

    @Insert
    void insertAll(Badge... badges);

    @Delete
    void delete(Badge badge);
}
