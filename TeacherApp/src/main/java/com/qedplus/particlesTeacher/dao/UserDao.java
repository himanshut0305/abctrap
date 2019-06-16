package com.qedplus.particlesTeacher.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.qedplus.particlesTeacher.db.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> findAll();

    @Query("SELECT * FROM User where username LIKE :username")
    User getUserByUsername(String username);

    @Insert
    long[] insertAll(User... users);

    @Delete
    void delete(User user);

    @Update
    void updateAll(User... users);
}
