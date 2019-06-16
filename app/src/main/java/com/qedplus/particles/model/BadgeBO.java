package com.qedplus.particles.model;

import android.support.annotation.NonNull;

import java.util.Date;

public class BadgeBO {
    @NonNull private String name;
    @NonNull private String type;
    @NonNull private String url;
    @NonNull private String description;
    @NonNull private Date acquiredOn;
    @NonNull private boolean seen;

    public BadgeBO(@NonNull String name, @NonNull String type, @NonNull String url, @NonNull String description, @NonNull Date acquiredOn, @NonNull boolean seen) {
        this.name = name;
        this.type = type;
        this.url = url;
        this.description = description;
        this.acquiredOn = acquiredOn;
        this.seen = seen;
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

    @NonNull
    public Date getAcquiredOn() {
        return acquiredOn;
    }

    public void setAcquiredOn(@NonNull Date acquiredOn) {
        this.acquiredOn = acquiredOn;
    }

    @NonNull
    public boolean isSeen() {
        return seen;
    }

    public void setSeen(@NonNull boolean seen) {
        this.seen = seen;
    }

    @Override
    public String toString() {
        return "BadgeBO{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", acquiredOn=" + acquiredOn +
                ", seen=" + seen +
                '}';
    }
}
