package com.qedplus.particles.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfirmInteractionResponse {
    @JsonProperty("question") private String question;
    @JsonProperty("affirmativeAnswer") private String affirmativeAnswer;
    @JsonProperty("negativeAnswer") private String negativeAnswer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAffirmativeAnswer() {
        return affirmativeAnswer;
    }

    public void setAffirmativeAnswer(String affirmativeAnswer) {
        this.affirmativeAnswer = affirmativeAnswer;
    }

    public String getNegativeAnswer() {
        return negativeAnswer;
    }

    public void setNegativeAnswer(String negativeAnswer) {
        this.negativeAnswer = negativeAnswer;
    }

    @Override
    public String toString() {
        return "ConfirmInteractionResponse{" +
                "question='" + question + '\'' +
                ", affirmativeAnswer='" + affirmativeAnswer + '\'' +
                ", negativeAnswer='" + negativeAnswer + '\'' +
                '}';
    }
}