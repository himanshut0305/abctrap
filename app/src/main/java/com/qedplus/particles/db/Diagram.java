package com.qedplus.particles.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Diagram {
    @PrimaryKey(autoGenerate = true)
    private int diagramId;

    private String preface;
    private String postface;
    @NonNull private String uri;

    public Diagram(String preface, @NonNull String uri, String postface) {
        this.preface = preface;
        this.postface = postface;
        this.uri = uri;
    }

    public int getDiagramId() {
        return diagramId;
    }

    public void setDiagramId(int diagramId) {
        this.diagramId = diagramId;
    }

    @NonNull
    public String getPreface() {
        return preface;
    }

    public void setPreface(@NonNull String preface) {
        this.preface = preface;
    }

    @NonNull
    public String getPostface() {
        return postface;
    }

    public void setPostface(@NonNull String postface) {
        this.postface = postface;
    }

    @NonNull
    public String getUri() {
        return uri;
    }

    public void setUri(@NonNull String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Diagram{" +
                "diagramId=" + diagramId +
                ", preface='" + preface + '\'' +
                ", postface='" + postface + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
