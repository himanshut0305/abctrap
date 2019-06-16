package com.qedplus.particles.model;

import android.util.Log;

import com.qedplus.particles.extras.MCQType;
import com.qedplus.particles.extras.QuestionCategory;
import com.qedplus.particles.extras.QuestionLevel;

import java.util.HashSet;

public class MultipleChoiceQuestionBO implements Question<HashSet<MCQOptionBO>> {
    private String question;
    private HashSet<MCQOptionBO> options;

    private Boolean isInteractive;
    private String instruction;
    private String genericExplanation;

    private DiagramBO diagram;

    @MCQType.Type private String mcqType;
    @QuestionLevel.Level private int level;
    @QuestionCategory.Category private String category;

    private boolean isAlternateDisplay;

    public MultipleChoiceQuestionBO(String question, HashSet<MCQOptionBO> options, String genericExplanation, @MCQType.Type String mcqType, String instruction, @QuestionLevel.Level int level, @QuestionCategory.Category String category) {
        this.question = question;
        this.options = options;
        this.mcqType = mcqType;
        this.instruction = instruction;
        this.genericExplanation = genericExplanation;
        this.level = level;
        this.category = category;
        this.isInteractive = false;
        this.isAlternateDisplay = false;
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.MultipleChoiceQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public HashSet<MCQOptionBO> getOptions() {
        return options;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getMcqType() {
        return mcqType;
    }

    public Boolean getInteractive() {
        return isInteractive;
    }

    public void setInteractive(Boolean interactive) {
        isInteractive = interactive;
    }

    @Override
    public Boolean isCorrectAnswer(HashSet<MCQOptionBO> answers) {
        HashSet<MCQOptionBO> correctOptions = new HashSet<>();

        for (MCQOptionBO option : options) {
            if (option.getCorrect()) {
                correctOptions.add(option);
                Log.e("CorrectAnswer", option.toString());
            }
        }

        if(isInteractive) {
            return correctOptions.contains(answers.iterator().next());
        }
        else {
            return answers.equals(correctOptions);
        }
    }

    public void setMcqType(String mcqType) {
        this.mcqType = mcqType;
    }

    public String getGenericExplanation() {
        return genericExplanation;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAlternateDisplay() {
        return isAlternateDisplay;
    }

    public void setAlternateDisplay(boolean alternateDisplay) {
        isAlternateDisplay = alternateDisplay;
    }

    public DiagramBO getDiagram() {
        return diagram;
    }

    public void setDiagram(DiagramBO diagram) {
        this.diagram = diagram;
    }

    @Override
    public String toString() {
        return "MultipleChoiceQuestionBO{" +
                "question='" + question + '\'' +
                ", options=" + options +
                ", isInteractive=" + isInteractive +
                ", instruction='" + instruction + '\'' +
                ", genericExplanation='" + genericExplanation + '\'' +
                ", diagram=" + diagram +
                ", mcqType='" + mcqType + '\'' +
                ", level=" + level +
                ", category='" + category + '\'' +
                ", isAlternateDisplay=" + isAlternateDisplay +
                '}';
    }
}
