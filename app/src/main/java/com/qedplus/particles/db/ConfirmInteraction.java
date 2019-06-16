package com.qedplus.particles.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class ConfirmInteraction {
    @PrimaryKey(autoGenerate = true)
    private int confirmInteractionId;

    @NonNull private String query;
    @NonNull private String affirmativeResponse;
    @NonNull private String negativeResponse;

    public ConfirmInteraction(@NonNull String query) {
        this.query = query;
        this.affirmativeResponse = "Yes";
        this.negativeResponse = "No";
    }

    public int getConfirmInteractionId() {
        return confirmInteractionId;
    }

    public void setConfirmInteractionId(int confirmInteractionId) {
        this.confirmInteractionId = confirmInteractionId;
    }

    @NonNull
    public String getQuery() {
        return query;
    }

    public void setQuery(@NonNull String query) {
        this.query = query;
    }

    @NonNull
    public String getAffirmativeResponse() {
        return affirmativeResponse;
    }

    public void setAffirmativeResponse(@NonNull String affirmativeResponse) {
        this.affirmativeResponse = affirmativeResponse;
    }

    @NonNull
    public String getNegativeResponse() {
        return negativeResponse;
    }

    public void setNegativeResponse(@NonNull String negativeResponse) {
        this.negativeResponse = negativeResponse;
    }

    @Override
    public String toString() {
        return "ConfirmInteraction{" +
                "confirmInteractionId=" + confirmInteractionId +
                ", query='" + query + '\'' +
                ", affirmativeResponse='" + affirmativeResponse + '\'' +
                ", negativeResponse='" + negativeResponse + '\'' +
                '}';
    }
}
