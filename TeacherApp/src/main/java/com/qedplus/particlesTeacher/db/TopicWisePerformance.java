package com.qedplus.particlesTeacher.db;

import android.arch.persistence.room.Entity;

@Entity
public class TopicWisePerformance {
    private long topicId;
    private long userId;

    private int conceptualQuestionsAsked;
    private int conceptualCorrectlyAnswered;
    private int memoryQuestionsAsked;
    private int memoryCorrectlyAnswered;
    private int numericalQuestionsAsked;
    private int numericalCorrectlyAnswered;

    private int level1QuestionsAsked;
    private int level1CorrectlyAnswered;
    private int level2QuestionsAsked;
    private int level2CorrectlyAnswered;
    private int level3QuestionsAsked;
    private int level3CorrectlyAnswered;
    private int level4QuestionsAsked;
    private int level4CorrectlyAnswered;
    private int level5QuestionsAsked;
    private int level5CorrectlyAnswered;

}
