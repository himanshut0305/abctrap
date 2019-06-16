package com.qedplus.particles.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
    indices = {
        @Index(
            value = {
                "multipleChoiceQuestionId"
            }
        )
    },
    foreignKeys = {
        @ForeignKey(
            entity = MultipleChoiceQuestion.class,
            parentColumns = "multipleChoiceQuestionId",
            childColumns = "multipleChoiceQuestionId"
        )
    }
)
public class MCQOption {
    @PrimaryKey(autoGenerate = true) private int mcqOptionId;
    @NonNull private String option;
    @NonNull private Boolean isCorrect;
    private String explanation;
    private long multipleChoiceQuestionId;

    public MCQOption(@NonNull String option, @NonNull Boolean isCorrect, String explanation, long multipleChoiceQuestionId) {
        this.option = option;
        this.isCorrect = isCorrect;
        this.explanation = explanation;
        this.multipleChoiceQuestionId = multipleChoiceQuestionId;
    }

    public int getMcqOptionId() {
        return mcqOptionId;
    }

    public void setMcqOptionId(int mcqOptionId) {
        this.mcqOptionId = mcqOptionId;
    }

    @NonNull
    public String getOption() {
        return option;
    }

    public void setOption(@NonNull String option) {
        this.option = option;
    }

    @NonNull
    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(@NonNull Boolean correct) {
        isCorrect = correct;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public long getMultipleChoiceQuestionId() {
        return multipleChoiceQuestionId;
    }

    public void setMultipleChoiceQuestionId(long multipleChoiceQuestionId) {
        this.multipleChoiceQuestionId = multipleChoiceQuestionId;
    }

    @Override
    public String toString() {
        return "MCQOption{" +
                "mcqOptionId=" + mcqOptionId +
                ", option='" + option + '\'' +
                ", isCorrect=" + isCorrect +
                ", explanation='" + explanation + '\'' +
                ", multipleChoiceQuestionId=" + multipleChoiceQuestionId +
                '}';
    }
}