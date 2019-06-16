package com.qedplus.particles.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(
    indices = {
        @Index(
            value = {
                "name",
                "acquiredOn"
            },
            unique = true
        )
    }
)
public class Badge {
    @PrimaryKey(autoGenerate = true)
    private int badgeId;

    @NonNull private String name;
    @NonNull private String type;
    @NonNull private String url;
    @NonNull private String description;
    private Date acquiredOn;
    private boolean seen;

    public Badge(@NonNull String name, @NonNull String type, @NonNull String url, @NonNull String description, @NonNull Date acquiredOn, boolean seen) {
        this.name = name;
        this.type = type;
        this.url = url;
        this.description = description;
        this.acquiredOn = acquiredOn;
        this.seen = seen;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public Date getAcquiredOn() {
        return acquiredOn;
    }

    public void setAcquiredOn(@NonNull Date acquiredOn) {
        this.acquiredOn = acquiredOn;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    @Override
    public String toString() {
        return "Badge{" +
                "badgeId=" + badgeId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", acquiredOn=" + acquiredOn +
                ", seen=" + seen +
                '}';
    }
}
