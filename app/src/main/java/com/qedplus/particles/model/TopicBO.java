package com.qedplus.particles.model;

import com.qedplus.particles.db.Topic;

import java.util.LinkedHashSet;

public class TopicBO {
    private String name;
    private String description;
    private boolean doesPrecedeQPT;
    private int topicStatus;
    private boolean isBookmarked;

    private LinkedHashSet<RevisionSlideBO> revisionSlideBOS;

    public TopicBO(String name, String description, boolean doesPrecedeQPT, @Topic.TopicStatus int topicStatus, boolean isBookmarked) {
        if (name == null) {
            throw new NullPointerException("Name is NULL");
        }

        if (description == null) {
            throw new NullPointerException("Description is NULL");
        }

        this.name = name;
        this.description = description;
        this.doesPrecedeQPT = doesPrecedeQPT;
        this.isBookmarked = isBookmarked;
        this.topicStatus = topicStatus;

        this.revisionSlideBOS = new LinkedHashSet<>();
    }

    public void addSlide(RevisionSlideBO revisionSlideBO) {
        this.revisionSlideBOS.add(revisionSlideBO);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDoesPrecedeQPT() {
        return doesPrecedeQPT;
    }

    public int getTopicStatus() {
        return topicStatus;
    }

    public boolean isLocked() {
        return topicStatus == Topic.TopicLocked;
    }

    public boolean isUnlocked() {
        return topicStatus == Topic.TopicUnlocked;
    }

    public boolean isActive() {
        return topicStatus == Topic.TopicActive;
    }

    public boolean isRevised() {
        return topicStatus == Topic.TopicRevised;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public LinkedHashSet<RevisionSlideBO> getRevisionSlideBOS() {
        return revisionSlideBOS;
    }

    @Override
    public String toString() {
        return "TopicRevision{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", doesPrecedeQPT=" + doesPrecedeQPT +
                ", topicStatus=" + topicStatus +
                ", isBookmarked=" + isBookmarked +
                ", revisionSlides=" + revisionSlideBOS +
                '}';
    }
}
