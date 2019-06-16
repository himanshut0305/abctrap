package com.qedplus.particles.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.qedplus.particles.extras.MCQType;
import com.qedplus.particles.extras.QuestionCategory;
import com.qedplus.particles.extras.QuestionLevel;

@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = Topic.class,
            parentColumns = "topicId",
            childColumns = "topicId"
        ),
        @ForeignKey(
            entity = Diagram.class,
            parentColumns = "diagramId",
            childColumns = "diagramId"
        )
    }
)
public class MultipleChoiceQuestion {
    @PrimaryKey(autoGenerate = true)
    private int multipleChoiceQuestionId;

    @NonNull private String question;
    @NonNull private String instruction;
    private long topicId;

    private int level;
    @NonNull private String category;
    @NonNull private String mcqType;

    private boolean alternateDisplay;
    private String explanation;
    private Long diagramId;

    public MultipleChoiceQuestion(@NonNull String question, @NonNull String instruction, String explanation, @NonNull @MCQType.Type String mcqType, @NonNull @QuestionCategory.Category String category, @QuestionLevel.Level int level, long topicId) {
        this.question = question;
        this.instruction = instruction;
        this.explanation = explanation;

        this.mcqType = mcqType;
        this.category = category;
        this.level = level;

        this.topicId = topicId;
        this.alternateDisplay = false;
    }

    public int getMultipleChoiceQuestionId() {
        return multipleChoiceQuestionId;
    }

    public void setMultipleChoiceQuestionId(int multipleChoiceQuestionId) {
        this.multipleChoiceQuestionId = multipleChoiceQuestionId;
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
    public String getMcqType() {
        return mcqType;
    }

    public void setMcqType(@NonNull String mcqType) {
        this.mcqType = mcqType;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
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

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public boolean isAlternateDisplay() {
        return alternateDisplay;
    }

    public void setAlternateDisplay(boolean alternateDisplay) {
        this.alternateDisplay = alternateDisplay;
    }

    public Long getDiagramId() {
        return diagramId;
    }

    public void setDiagramId(Long diagramId) {
        this.diagramId = diagramId;
    }

    @Override
    public String toString() {
        return "MultipleChoiceQuestion{" +
                "multipleChoiceQuestionId=" + multipleChoiceQuestionId +
                ", question='" + question + '\'' +
                ", instruction='" + instruction + '\'' +
                ", topicId=" + topicId +
                ", level=" + level +
                ", category='" + category + '\'' +
                ", mcqType='" + mcqType + '\'' +
                ", alternateDisplay=" + alternateDisplay +
                ", explanation='" + explanation + '\'' +
                ", diagramId=" + diagramId +
                '}';
    }
}