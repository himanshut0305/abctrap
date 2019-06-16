package com.qedplus.particles.model;

import android.support.annotation.NonNull;

import com.qedplus.particles.db.Announcement;

import java.util.Date;

public class AnnouncementBO {
    @NonNull private String announcement;
    @NonNull private Date madeOn;
    @NonNull private String teacher;
    @NonNull private boolean seen;

    public AnnouncementBO(@NonNull String announcement, @NonNull Date madeOn, @NonNull String teacher, @NonNull boolean seen) {
        this.announcement = announcement;
        this.madeOn = madeOn;
        this.teacher = teacher;
        this.seen = seen;
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

    public String toString() {
        return "AnnouncementBO{" +
                "announcement='" + announcement + '\'' +
                ", madeOn=" + madeOn +
                ", teacher='" + teacher + '\'' +
                ", seen=" + seen +
                '}';
    }

    public Announcement getDBObject() {
        Announcement a = new Announcement(announcement, madeOn, teacher);
        a.setSeen(seen);
        return a;
    }
}