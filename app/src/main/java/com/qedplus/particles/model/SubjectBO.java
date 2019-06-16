package com.qedplus.particles.model;

public class SubjectBO {
    private String subjectName;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "SubjectBO{" +
                "subjectName='" + subjectName + '\'' +
                '}';
    }
}
