package com.qedplus.particles.model;

public class RevealInteractionBO {
    String query;
    String answer;

    public RevealInteractionBO(String query, String answer) {
        this.query = query;
        this.answer = answer;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "RevealInteractionBO{" +
                "query='" + query + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}