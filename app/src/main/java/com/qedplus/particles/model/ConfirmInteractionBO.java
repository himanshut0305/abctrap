package com.qedplus.particles.model;

public class ConfirmInteractionBO {
    String query;
    String affirmativeResponse;
    String negativeResponse;

    public ConfirmInteractionBO(String query, String affirmativeResponse, String negativeResponse) {
        this.query = query;
        this.affirmativeResponse = affirmativeResponse;
        this.negativeResponse = negativeResponse;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getAffirmativeResponse() {
        return affirmativeResponse;
    }

    public void setAffirmativeResponse(String affirmativeResponse) {
        this.affirmativeResponse = affirmativeResponse;
    }

    public String getNegativeResponse() {
        return negativeResponse;
    }

    public void setNegativeResponse(String negativeResponse) {
        this.negativeResponse = negativeResponse;
    }

    @Override
    public String toString() {
        return "ConfirmInteractionBO{" +
                "query='" + query + '\'' +
                ", affirmativeResponse='" + affirmativeResponse + '\'' +
                ", negativeResponse='" + negativeResponse + '\'' +
                '}';
    }
}
