package com.qedplus.particles.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.qedplus.particles.db.MCQOption;
import com.qedplus.particles.db.MultipleChoiceQuestion;

import java.util.List;

@Dao
public interface MCQOptionDao {
    @Query("Select * from MCQOption where multipleChoiceQuestionId LIKE :multipleChoiceQuestionId")
    List<MCQOption> findAllByQuestion(int multipleChoiceQuestionId);

    @Insert
    void insertAll(MCQOption... mcqOptions);

    @Delete
    void delete(MCQOption mcqOption);
}
