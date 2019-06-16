package com.qedplus.particles.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
public class RevealInteractionResponse {
    @JsonProperty("question") private String question;
    @JsonProperty("answer") private String answer;

    public String getQuestion() {
        return question
                .replace("<formula>", "\\( ")
                .replace("</formula>", " \\)")
                .replace("cfrac", "frac")
                .replace("\\implies", "\\Rightarrow");
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        String ans = "<font color='#ffffff'>" + answer + "</font>";
        return ans
                .replace("<formula>", "\\( ")
                .replace("</formula>", " \\)")
                .replace("cfrac", "frac")
                .replace("\\implies", "\\Rightarrow");
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "RevealInteractionResponse{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
