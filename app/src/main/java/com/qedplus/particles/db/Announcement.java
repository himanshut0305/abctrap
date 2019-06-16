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
                "announcement",
                "madeOn",
                "teacher"
            },
            unique = true
        )
    }
)
public class Announcement {
    @PrimaryKey(autoGenerate = true)
    private int announcementId;

    @NonNull private String announcement;
    @NonNull private Date madeOn;
    @NonNull private String teacher;
    private boolean seen;

    public Announcement(@NonNull String announcement, @NonNull Date madeOn, @NonNull String teacher) {
        this.announcement = announcement;
        this.madeOn = madeOn;
        this.teacher = teacher;
        this.seen = false;
    }

    public int getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
    }

    @NonNull
    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(@NonNull String announcement) {
        this.announcement = announcement;
    }

    @NonNull
    public Date getMadeOn() {
        return madeOn;
    }

    public void setMadeOn(@NonNull Date madeOn) {
        this.madeOn = madeOn;
    }

    @NonNull
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(@NonNull String teacher) {
        this.teacher = teacher;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "announcementId=" + announcementId +
                ", announcement='" + announcement + '\'' +
                ", madeOn=" + madeOn +
                ", teacher='" + teacher + '\'' +
                ", seen=" + seen +
                '}';
    }
}
