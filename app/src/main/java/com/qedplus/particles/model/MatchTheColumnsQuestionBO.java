package com.qedplus.particles.model;

import java.util.HashSet;

public class MatchTheColumnsQuestionBO implements Question {
    public class MTCQOption {
        String option;
        String matchingOption;
    }

    private String question;
    private String instruction;
    private HashSet<MTCQOption> options;

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.MatchTheColumnQuestion;
    }

    @Override
    public Boolean isCorrectAnswer(Object o) {
        return null;
    }
}
