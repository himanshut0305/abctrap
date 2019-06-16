package com.qedplus.particles.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qedplus.particles.db.Book;

import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM Book where name LIKE :name")
    Book findByName (String name);

    @Query("SELECT * FROM Book where schoolYear LIKE :schoolYear")
    List<Book> findBySchoolYear(int schoolYear);


    @Query("SELECT * FROM Book where subjectId LIKE :subjectId and schoolYear LIKE :schoolYear")
    Book findBySubjectAndSchoolYear(int subjectId, int schoolYear);

    @Insert
    long[] insertAll(Book... books);

    @Delete
    void delete(Book book);
}
