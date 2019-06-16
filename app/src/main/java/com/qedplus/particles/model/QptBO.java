package com.qedplus.particles.model;

import android.support.annotation.NonNull;

import com.qedplus.particles.db.QPT;

public class QptBO {
    private int qptIndex;
    private long chapterId;
    private int numOfQues;
    private int qptStatus;

    public QptBO(int qptIndex, long chapterId, int numOfQues, @QPT.QPTStatus int qptStatus) {
        this.qptIndex = qptIndex;
        this.chapterId = chapterId;
        this.numOfQues = numOfQues;
        this.qptStatus = qptStatus;
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

    public void setChapterId(int chapterId) {
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

    public boolean isLocked() {
        return qptStatus == QPT.QPTLocked;
    }
    public boolean isUnlocked() {
        return qptStatus == QPT.QPTUnlocked;
    }
    public boolean isAttempted() {
        return qptStatus == QPT.QPTAttempted;
    }
    public boolean isAced() {
        return qptStatus == QPT.QPTAced;
    }

    @Override
    public String toString() {
        return "QptBO{" +
                "qptIndex=" + qptIndex +
                ", chapterId=" + chapterId +
                ", numOfQues=" + numOfQues +
                ", qptStatus=" + qptStatus +
                '}';
    }
}
