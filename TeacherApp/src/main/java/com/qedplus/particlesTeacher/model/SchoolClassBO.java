package com.qedplus.particlesTeacher.model;

public class SchoolClassBO {
    private String className;

    public SchoolClassBO(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "SubjectClassBO{" +
                "className='" + className + '\'' +
                '}';
    }
}
