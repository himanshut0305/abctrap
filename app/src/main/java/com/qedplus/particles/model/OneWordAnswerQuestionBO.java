package com.qedplus.particles.model;

import com.qedplus.particles.extras.QuestionCategory;
import com.qedplus.particles.extras.QuestionLevel;

import java.util.HashSet;

public class OneWordAnswerQuestionBO implements Question<String> {
    private String instruction;
    private String question;
    private String explanation;
    private HashSet<String> acceptableAnswers = new HashSet<>();

    @QuestionLevel.Level private int level;
    @QuestionCategory.Category private String category;

    public OneWordAnswerQuestionBO(String question, HashSet<String> acceptableAnswers, String instruction, String explanation, @QuestionLevel.Level int level, @QuestionCategory.Category String category) {
        this.instruction = instruction;
        this.question = question;
        this.explanation = explanation;

        for (String answer : acceptableAnswers) {
            this.acceptableAnswers.add(answer.toLowerCase());
        }

        this.category = category;
        this.level = level;
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.OneWordAnswerQuestion;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public HashSet<String> getAcceptableAnswers() {
        return acceptableAnswers;
    }

    public void setAcceptableAnswers(HashSet<String> acceptableAnswers) {
        this.acceptableAnswers = acceptableAnswers;
    }

    public String getExplanation() {
        return explanation;
    }

    @Override
    public Boolean isCorrectAnswer(String answer) {
        return acceptableAnswers.contains(answer.toLowerCase().trim());
    }

    @Override
    public String toString() {
        return "OneWordAnswerQuestionBO{" +
                "instruction='" + instruction + '\'' +
                ", question='" + question + '\'' +
                ", explanation='" + explanation + '\'' +
                ", acceptableAnswers=" + acceptableAnswers +
                ", level=" + level +
                ", category='" + category + '\'' +
                '}';
    }
}
