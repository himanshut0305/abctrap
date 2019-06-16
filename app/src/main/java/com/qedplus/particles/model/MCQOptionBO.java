package com.qedplus.particles.model;

import android.support.annotation.NonNull;

public class MCQOptionBO {
    private String option;
    private Boolean isCorrect;
    private String explanation;

    public MCQOptionBO(@NonNull String option, @NonNull Boolean isCorrect, @NonNull String explanation) {
        this.option = option;
        this.isCorrect = isCorrect;
        this.explanation = explanation;
    }

    public String getOption() {
        return option;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public String getExplanation() {
        return explanation;
    }

    @Override
    public String toString() {
        return "MCQOption{" +
                "option='" + option + '\'' +
                ", isCorrect=" + isCorrect +
                ", explanation='" + explanation + '\'' +
                '}';
    }
}
