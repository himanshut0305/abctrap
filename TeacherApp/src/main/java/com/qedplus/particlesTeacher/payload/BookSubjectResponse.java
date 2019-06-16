package com.qedplus.particlesTeacher.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookSubjectResponse {
    @JsonProperty("book") public BookResponse book;
    @JsonProperty("subject") public SubjectResponse subject;

    public BookResponse getBook() {
        return book;
    }

    public void setBook(BookResponse book) {
        this.book = book;
    }

    public SubjectResponse getSubject() {
        return subject;
    }

    public void setSubject(SubjectResponse subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "BookSubjectResponse{" +
                "book=" + book +
                ", subject=" + subject +
                '}';
    }
}
