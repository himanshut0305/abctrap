package com.qedplus.particlesTeacher.model;

import java.util.LinkedHashSet;

public class ChapterBO {
    private String name;
    private int chapterIndex;
    private LinkedHashSet<TopicBO> revisions;

    public ChapterBO() { }

    public ChapterBO(String name, int chapterIndex) {
        this.name = name;
        this.chapterIndex = chapterIndex;
        this.revisions = new LinkedHashSet<>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChapterIndex() {
        return "Chapter " + chapterIndex;
    }

    public int getIndex() { return chapterIndex; }

    public void setChapterIndex(int chapterIndex) {
        this.chapterIndex = chapterIndex;
    }

    public LinkedHashSet<TopicBO> getRevisions() {
        return revisions;
    }

    public void setRevisions(LinkedHashSet<TopicBO> revisions) {
        this.revisions = revisions;
    }

    public void addRevision(TopicBO revision) {
        this.revisions.add(revision);
    }



    @Override
    public String toString() {
        return "ChapterBO{" +
                "name='" + name + '\'' +
                ", chapterIndex=" + chapterIndex +
                ", revisions=" + revisions +

                '}';
    }
}
