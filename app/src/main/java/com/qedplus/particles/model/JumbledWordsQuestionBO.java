package com.qedplus.particles.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JumbledWordsQuestionBO implements Question {
    private String instruction;
    private String question;
    private String explanation;

    public JumbledWordsQuestionBO(String instruction, String question, String explanation) {
        if (instruction == null) {
            throw new NullPointerException("Instruction String is NULL");
        }

        if (question == null) {
            throw new NullPointerException("Question String is NULL");
        }

        if (explanation == null) {
            throw new NullPointerException("Explanation is NULL");
        }

        this.instruction = instruction;
        this.question = question;
        this.explanation = explanation;
    }


    @Override
    public QuestionType getQuestionType() {
        return QuestionType.JumbledWordsQuestion;
    }

    @Override
    public Boolean isCorrectAnswer(Object o) {
        return null;
    }

    @NonNull
    public List<String> getQuestion() {
        String[] words = question.split("\\W+");
        ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(words));
        Collections.shuffle(wordList);

        return wordList;
    }

    @NonNull
    public Boolean isCorrectAnswer(@NonNull String answer) {
        return question.equals(answer);
    }

    @Override
    public String toString() {
        return "JumbledWordsQuestion{" +
                "instruction='" + instruction + '\'' +
                ", question='" + question + '\'' +
                ", explanation='" + explanation + '\'' +
                '}';
    }
}
