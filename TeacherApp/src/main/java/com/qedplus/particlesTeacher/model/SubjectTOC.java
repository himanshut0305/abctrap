package com.qedplus.particlesTeacher.model;

import java.util.HashSet;

public class SubjectTOC {
    private String name;
    private HashSet<ChapterBO> chapters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<ChapterBO> getChapters() {
        return chapters;
    }

    public void setChapters(HashSet<ChapterBO> chapters) {
        this.chapters = chapters;
    }
}
