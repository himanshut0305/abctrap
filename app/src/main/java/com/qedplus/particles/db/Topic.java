package com.qedplus.particles.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;
import java.util.Date;

@Entity(
    indices = {
        @Index(
            value = {
                "name",
                "chapterId"
            },
            unique = true
        ),
        @Index(
            value = {
                "topicIndex",
                "chapterId"
            },
            unique = true
        ),
        @Index(value = { "chapterId" }),
        @Index(value = { "qptId" })
    },
    foreignKeys = {
        @ForeignKey(
            entity = Chapter.class,
            parentColumns = "chapterId",
            childColumns = "chapterId"
        )
//        @ForeignKey(
//            entity = QPT.class,
//            parentColumns = "qptId",
//            childColumns = "qptId"
//        )
    }
)
public class Topic {
    @Ignore public static final int TopicLocked = 0;
    @Ignore public static final int TopicUnlocked = 1;
    @Ignore public static final int TopicActive = 2;
    @Ignore public static final int TopicRevised = 3;

    @PrimaryKey(autoGenerate = true)
    private int topicId;

    @NonNull private String name;
    @NonNull private String description;
    private int topicIndex;
    private long chapterId;
    private boolean doesPrecedeQPT;
    private int topicStatus;
    private boolean isBookmarked;
    private long qptId;
    private int version;

    @NonNull private Date updatedOn;

    @IntDef({TopicLocked, TopicUnlocked, TopicActive, TopicRevised})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TopicStatus { }

    public Topic(@NonNull String name, int topicIndex, @NonNull String description, long chapterId, boolean doesPrecedeQPT, int version) {
        this.name = name;
        this.topicIndex = topicIndex;
        this.description = description;
        this.chapterId = chapterId;
        this.doesPrecedeQPT = doesPrecedeQPT;
        this.isBookmarked = false;
        this.topicStatus = TopicLocked;
        this.version = version;

        this.updatedOn = Calendar.getInstance().getTime();
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public long getChapterId() {
        return chapterId;
    }

    public void setChapterId(long chapterId) {
        this.chapterId = chapterId;
    }

    public int getTopicIndex() {
        return topicIndex;
    }

    public void setTopicIndex(int topicIndex) {
        this.topicIndex = topicIndex;
    }

    public boolean isDoesPrecedeQPT() {
        return doesPrecedeQPT;
    }

    public void setDoesPrecedeQPT(boolean doesPrecedeQPT) {
        this.doesPrecedeQPT = doesPrecedeQPT;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.isBookmarked = bookmarked;
    }

    public long getQptId() {
        return qptId;
    }

    public void setQptId(long qptId) {
        this.qptId = qptId;
    }

    public int getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(@TopicStatus int topicStatus) {
        this.topicStatus = topicStatus;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @NonNull
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(@NonNull Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicId=" + topicId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", topicIndex=" + topicIndex +
                ", chapterId=" + chapterId +
                ", doesPrecedeQPT=" + doesPrecedeQPT +
                ", topicStatus=" + topicStatus +
                ", isBookmarked=" + isBookmarked +
                ", qptId=" + qptId +
                ", version=" + version +
                ", updatedOn=" + updatedOn +
                '}';
    }
}