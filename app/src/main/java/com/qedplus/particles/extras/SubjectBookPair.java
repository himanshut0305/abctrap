package com.qedplus.particles.extras;

import java.io.Serializable;

public class SubjectBookPair implements Serializable {
    private String subject;
    private String book;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "SubjectBookPair{" +
                "subject='" + subject + '\'' +
                ", book='" + book + '\'' +
                '}';
    }
}