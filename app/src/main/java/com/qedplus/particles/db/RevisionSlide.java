package com.qedplus.particles.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import com.qedplus.particles.extras.SlideType;
import com.qedplus.particles.extras.Utility;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Entity(
        indices = {
                @Index(
                        value = {
                                "slideIndex",
                                "topicId"
                        },
                        unique = true
                ),
                @Index(value = { "topicId" }),
                @Index(value = { "revisionPoint1Id" }),
                @Index(value = { "revisionPoint2Id" }),
                @Index(value = { "revisionPoint3Id" }),
                @Index(value = { "diagramId" }),
                @Index(value = { "revealInteractionId" }),
                @Index(value = { "confirmInteractionId" }),
                @Index(value = { "mcqId" }),
                @Index(value = { "owaqId" })
        },
        foreignKeys = {
                @ForeignKey(
                        entity = Topic.class,
                        parentColumns = "topicId",
                        childColumns = "topicId"
                ),
                @ForeignKey(
                        entity = RevisionPoint.class,
                        parentColumns = "revisionPointId",
                        childColumns = "revisionPoint1Id"
                ),
                @ForeignKey(
                        entity = RevisionPoint.class,
                        parentColumns = "revisionPointId",
                        childColumns = "revisionPoint2Id"
                ),
                @ForeignKey(
                        entity = RevisionPoint.class,
                        parentColumns = "revisionPointId",
                        childColumns = "revisionPoint3Id"
                ),
                @ForeignKey(
                        entity = Diagram.class,
                        parentColumns = "diagramId",
                        childColumns = "diagramId"
                ),
                @ForeignKey(
                        entity = RevealInteraction.class,
                        parentColumns = "revealInteractionId",
                        childColumns = "revealInteractionId"
                ),
                @ForeignKey(
                        entity = ConfirmInteraction.class,
                        parentColumns = "confirmInteractionId",
                        childColumns = "confirmInteractionId"
                ),
                @ForeignKey(
                        entity = MultipleChoiceQuestion.class,
                        parentColumns = "multipleChoiceQuestionId",
                        childColumns = "mcqId"
                ),
                @ForeignKey(
                        entity = OneWordAnswerQuestion.class,
                        parentColumns = "oneWordAnswerQuestionId",
                        childColumns = "owaqId"
                )
        })
public class RevisionSlide {
    @PrimaryKey(autoGenerate = true)
    private int revisionSlideId;

    private int slideIndex;
    @NonNull private Long topicId;
    @NonNull private String slideType;

    private Long revisionPoint1Id;
    private Long revisionPoint2Id;
    private Long revisionPoint3Id;

    private Long diagramId;
    private Long revealInteractionId;
    private Long confirmInteractionId;

    private Long mcqId;
    private Long owaqId;
    private boolean isInteractive;

    public RevisionSlide(int slideIndex, @NonNull Long topicId, @NonNull @SlideType.Type String slideType) {
        this.slideIndex = slideIndex;
        this.topicId = topicId;
        this.slideType = slideType;
        this.isInteractive = false;
    }

    public int getRevisionSlideId() {
        return revisionSlideId;
    }

    public void setRevisionSlideId(int revisionSlideId) {
        this.revisionSlideId = revisionSlideId;
    }

    public int getSlideIndex() {
        return slideIndex;
    }

    public void setSlideIndex(int slideIndex) {
        this.slideIndex = slideIndex;
    }

    @NonNull
    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(@NonNull Long topicId) {
        this.topicId = topicId;
    }

    @NonNull
    public String getSlideType() {
        return slideType;
    }

    public void setSlideType(@NonNull String slideType) {
        this.slideType = slideType;
    }

    public Long getRevisionPoint1Id() {
        return revisionPoint1Id;
    }

    public void setRevisionPoint1Id(Long revisionPoint1Id) {
        this.revisionPoint1Id = revisionPoint1Id;
    }

    public Long getRevisionPoint2Id() {
        return revisionPoint2Id;
    }

    public void setRevisionPoint2Id(Long revisionPoint2Id) {
        if(hasNoSecondPart())
            this.revisionPoint2Id = revisionPoint2Id;
    }

    public Long getRevisionPoint3Id() {
        return revisionPoint3Id;
    }

    public void setRevisionPoint3Id(Long revisionPoint3Id) {
        this.revisionPoint3Id = revisionPoint3Id;
    }

    public Long getDiagramId() {
        return diagramId;
    }

    public void setDiagramId(Long diagramId) {
        this.diagramId = diagramId;
    }

    public Long getRevealInteractionId() {
        return revealInteractionId;
    }

    public void setRevealInteractionId(Long revealInteractionId) {
        if(hasNoSecondPart())
            this.revealInteractionId = revealInteractionId;
    }

    public Long getConfirmInteractionId() {
        return confirmInteractionId;
    }

    public void setConfirmInteractionId(Long confirmInteractionId) {
        if(hasNoSecondPart())
            this.confirmInteractionId = confirmInteractionId;
    }

    public Long getMcqId() {
        return mcqId;
    }

    public void setMcqId(Long mcqId) {
        if(hasNoOtherQuestion())
            this.mcqId = mcqId;
    }

    public Long getOwaqId() {
        return owaqId;
    }

    public void setOwaqId(Long owaqId) {
        if(hasNoOtherQuestion())
            this.owaqId = owaqId;
    }

    public boolean isInteractive() {
        return isInteractive;
    }

    public void setInteractive(boolean interactive) {
        isInteractive = interactive;
    }

    private boolean hasNoSecondPart() {
        if(revisionPoint2Id != null)
            return false;

        if(diagramId != null)
            return false;

        if(revealInteractionId != null)
            return false;

        if(confirmInteractionId != null)
            return false;

        return true;
    }

    private boolean hasNoOtherQuestion() {
        if(mcqId != null)
            return false;

        if(owaqId != null)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "RevisionSlide{" +
                "revisionSlideId=" + revisionSlideId +
                ", slideIndex=" + slideIndex +
                ", topicId=" + topicId +
                ", slideType='" + slideType + '\'' +
                ", revisionPoint1Id=" + revisionPoint1Id +
                ", revisionPoint2Id=" + revisionPoint2Id +
                ", revisionPoint3Id=" + revisionPoint3Id +
                ", diagramId=" + diagramId +
                ", revealInteractionId=" + revealInteractionId +
                ", confirmInteractionId=" + confirmInteractionId +
                ", mcqId=" + mcqId +
                ", owaqId=" + owaqId +
                '}';
    }
}
