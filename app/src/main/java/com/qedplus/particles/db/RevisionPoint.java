package com.qedplus.particles.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class RevisionPoint {
    @PrimaryKey(autoGenerate = true)
    private int revisionPointId;

    private String preface;
    private String explanation;
    private String postface;

    public RevisionPoint(String preface, String explanation, String postface) {
        this.preface = preface;
        this.explanation = explanation;
        this.postface = postface;
    }

    public int getRevisionPointId() {
        return revisionPointId;
    }

    public void setRevisionPointId(int revisionPointId) {
        this.revisionPointId = revisionPointId;
    }

    public String getPreface() {
        return preface;
    }

    public void setPreface(String preface) {
        this.preface = preface;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getPostface() {
        return postface;
    }

    public void setPostface(String postface) {
        this.postface = postface;
    }

    @Override
    public String toString() {
        return "RevisionPoint{" +
                "revisionPointId=" + revisionPointId +
                ", preface='" + preface + '\'' +
                ", explanation='" + explanation + '\'' +
                ", postface='" + postface + '\'' +
                '}';
    }
}
