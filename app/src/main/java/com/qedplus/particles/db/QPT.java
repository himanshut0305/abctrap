package com.qedplus.particles.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Entity(
    indices = {
        @Index(
            value = {
                "qptIndex",
                "chapterId"
            },
            unique = true
        ),
        @Index("chapterId")
    },
    foreignKeys = {
        @ForeignKey(
            entity = Chapter.class,
            parentColumns = "chapterId",
            childColumns = "chapterId"
        )
    }
)
public class QPT {
    @Ignore public static final int QPTLocked = 0;
    @Ignore public static final int QPTUnlocked = 1;
    @Ignore public static final int QPTAttempted = 2;
    @Ignore public static final int QPTAced = 3;

    @PrimaryKey(autoGenerate = true)
    private int qptId;

    private int qptIndex;
    private long chapterId;
    private int numOfQues;
    private int qptStatus;

    @IntDef({QPTLocked, QPTUnlocked, QPTAttempted, QPTAced})
    @Retention(RetentionPolicy.SOURCE)
    public @interface QPTStatus { }

    public QPT(int qptIndex, long chapterId, int numOfQues) {
        this.qptIndex = qptIndex;
        this.chapterId = chapterId;
        this.numOfQues = numOfQues;
        this.qptStatus = QPTLocked;
    }

    public int getQptId() {
        return qptId;
    }

    public void setQptId(int qptId) {
        this.qptId = qptId;
    }

    public int getQptIndex() {
        return qptIndex;
    }

    public void setQptIndex(int qptIndex) {
        this.qptIndex = qptIndex;
    }

    public long getChapterId() {
        return chapterId;
    }

    public void setChapterId(long chapterId) {
        this.chapterId = chapterId;
    }

    public int getNumOfQues() {
        return numOfQues;
    }

    public void setNumOfQues(int numOfQues) {
        this.numOfQues = numOfQues;
    }

    public int getQptStatus() {
        return qptStatus;
    }

    public void setQptStatus(int qptStatus) {
        this.qptStatus = qptStatus;
    }
}
