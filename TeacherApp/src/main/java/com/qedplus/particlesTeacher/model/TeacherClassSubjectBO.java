package com.qedplus.particlesTeacher.model;

public class TeacherClassSubjectBO {
    String className;
    String subjectName;
    String currentChapter;
    String currentTopic;

    int schoolYear;

    public TeacherClassSubjectBO(String className, String subjectName, String currentChapter, String currentTopic, int schoolYear) {
        this.className = className;
        this.subjectName = subjectName;
        this.currentChapter = currentChapter;
        this.currentTopic = currentTopic;

        this.schoolYear = schoolYear;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCurrentChapter() {
        return currentChapter;
    }

    public void setCurrentChapter(String currentChapter) {
        this.currentChapter = currentChapter;
    }

    public String getCurrentTopic() {
        return currentTopic;
    }

    public void setCurrentTopic(String currentTopic) {
        this.currentTopic = currentTopic;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    @Override
    public String toString() {
        return "TeacherClassSubjectBO{" +
                "className='" + className + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", currentChapter='" + currentChapter + '\'' +
                ", currentTopic='" + currentTopic + '\'' +
                ", schoolYear=" + schoolYear +
                '}';
    }
}