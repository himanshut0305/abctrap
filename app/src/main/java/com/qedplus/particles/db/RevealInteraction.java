package com.qedplus.particles.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class RevealInteraction {
    @PrimaryKey(autoGenerate = true)
    private int revealInteractionId;

    @NonNull private String query;
    @NonNull private String answer;

    public RevealInteraction(@NonNull String query, @NonNull String answer) {
        this.query = query;
        this.answer = answer;
    }

    public int getRevealInteractionId() {
        return revealInteractionId;
    }

    public void setRevealInteractionId(int revealInteractionId) {
        this.revealInteractionId = revealInteractionId;
    }

    @NonNull
    public String getQuery() {
        return query;
    }

    public void setQuery(@NonNull String query) {
        this.query = query;
    }

    @NonNull
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(@NonNull String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "RevealInteraction{" +
                "revealInteractionId=" + revealInteractionId +
                ", query='" + query + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
