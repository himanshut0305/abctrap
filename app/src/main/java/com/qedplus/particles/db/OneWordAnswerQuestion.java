package com.qedplus.particles.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.qedplus.particles.extras.QuestionCategory;
import com.qedplus.particles.extras.QuestionLevel;

@Entity
public class OneWordAnswerQuestion {
    @PrimaryKey(autoGenerate = true)
    private int oneWordAnswerQuestionId;

    @NonNull private String question;
    @NonNull private String instruction;
    @NonNull private String acceptableAnswers;

    private int level;
    @NonNull private String category;
    private long topicId;

    private String explanation;
    private Long diagramId;

    public OneWordAnswerQuestion(@NonNull String question, @NonNull String instruction, @NonNull String acceptableAnswers, @QuestionLevel.Level int level, @NonNull @QuestionCategory.Category String category, String explanation, long topicId) {
        this.question = question;
        this.instruction = instruction;
        this.acceptableAnswers = acceptableAnswers;
        this.level = level;
        this.category = category;
        this.explanation = explanation;
        this.topicId = topicId;
    }

    public int getOneWordAnswerQuestionId() {
        return oneWordAnswerQuestionId;
    }

    public void setOneWordAnswerQuestionId(int oneWordAnswerQuestionId) {
        this.oneWordAnswerQuestionId = oneWordAnswerQuestionId;
    }

    @NonNull
    public String getQuestion() {
        return question;
    }

    public void setQuestion(@NonNull String question) {
        this.question = question;
    }

    @NonNull
    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(@NonNull String instruction) {
        this.instruction = instruction;
    }

    @NonNull
    public String getAcceptableAnswers() {
        return acceptableAnswers;
    }

    public void setAcceptableAnswers(@NonNull String acceptableAnswers) {
        this.acceptableAnswers = acceptableAnswers;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @NonNull
    public String getCategory() {
        return category;
    }

    public void setCategory(@NonNull String category) {
        this.category = category;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public Long getDiagramId() {
        return diagramId;
    }

    public void setDiagramId(Long diagramId) {
        this.diagramId = diagramId;
    }

    @Override
    public String toString() {
        return "OneWordAnswerQuestion{" +
                "oneWordAnswerQuestionId=" + oneWordAnswerQuestionId +
                ", question='" + question + '\'' +
                ", instruction='" + instruction + '\'' +
                ", acceptableAnswers='" + acceptableAnswers + '\'' +
                ", level=" + level +
                ", category='" + category + '\'' +
                ", topicId=" + topicId +
                ", explanation='" + explanation + '\'' +
                ", diagramId=" + diagramId +
                '}';
    }
}
