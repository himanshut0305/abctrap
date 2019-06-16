package com.qedplus.particles.model;

public interface Question <E>{
    enum QuestionType { MultipleChoiceQuestion, MatchTheColumnQuestion, OneWordAnswerQuestion, JumbledWordsQuestion }

    QuestionType getQuestionType();
    Boolean isCorrectAnswer(E e);
}
