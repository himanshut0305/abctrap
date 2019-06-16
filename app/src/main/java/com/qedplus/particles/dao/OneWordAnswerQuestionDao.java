package com.qedplus.particles.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.qedplus.particles.db.MultipleChoiceQuestion;
import com.qedplus.particles.db.OneWordAnswerQuestion;

import java.util.List;

@Dao
public interface OneWordAnswerQuestionDao {
    @Query("Select * from onewordanswerquestion where oneWordAnswerQuestionId LIKE :oneWordAnswerQuestionId")
    OneWordAnswerQuestion findById(long oneWordAnswerQuestionId);

    @Query("Select * from OneWordAnswerQuestion where topicId LIKE :topicId")
    List<OneWordAnswerQuestion> findAllByTopic(long topicId);

    @Insert
    long[] insertAll(OneWordAnswerQuestion... oneWordAnswerQuestions);

    @Delete
    void delete(OneWordAnswerQuestion oneWordAnswerQuestion);

    @Update
    void updateAll(OneWordAnswerQuestion... oneWordAnswerQuestions);
}
