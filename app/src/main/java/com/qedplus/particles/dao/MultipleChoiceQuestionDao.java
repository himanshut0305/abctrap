package com.qedplus.particles.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.qedplus.particles.db.ConfirmInteraction;
import com.qedplus.particles.db.MultipleChoiceQuestion;
import com.qedplus.particles.db.OneWordAnswerQuestion;
import com.qedplus.particles.db.Topic;

import java.util.List;

@Dao
public interface MultipleChoiceQuestionDao {
    @Query("Select * from MultipleChoiceQuestion where multipleChoiceQuestionId LIKE :multipleChoiceQuestionId")
    MultipleChoiceQuestion findById(long multipleChoiceQuestionId);

    @Query("Select * from MultipleChoiceQuestion where topicId LIKE :topicId")
    List<MultipleChoiceQuestion> findAllByTopic(long topicId);

    @Insert
    long[] insertAll(MultipleChoiceQuestion... multipleChoiceQuestions);

    @Delete
    void delete(MultipleChoiceQuestion multipleChoiceQuestion);

    @Update
    void updateAll(OneWordAnswerQuestion... oneWordAnswerQuestions);
}
