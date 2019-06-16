package com.qedplus.particles.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qedplus.particles.extras.MCQType;
import com.qedplus.particles.extras.QuestionCategory;
import com.qedplus.particles.extras.QuestionLevel;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionResponse {
    @JsonProperty("question") private String question;
    @JsonProperty("questionType") private String questionType;
    @JsonProperty("options") private OptionResponse[] options;

    @JsonProperty("category") private String category;
    @JsonProperty("level") private int level;
    @JsonProperty("instruction") private String instruction;

    @JsonProperty("diagram") private DiagramResponse diagram;

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

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public OptionResponse[] getOptions() {
        return options;
    }

    public void setOptions(OptionResponse[] options) {
        this.options = options;
    }

    public String getMCQType() {
        if(questionType.equals("MCQ_Multiple_Answer")) {
            return MCQType.MULTIPLE_ANSWER;
        }
        else if(questionType.equals("MCQ_Single_Answer")) {
            return MCQType.SINGLE_ANSWER;
        }
        else if(questionType.equals("MCQ_True_False")) {
            return MCQType.TRUE_FALSE;
        }
        else {
            return MCQType.MULTIPLE_ANSWER;
        }
    }

    public String getCategory() {
        if(category.equals("CONCEPTUAL")) {
            return QuestionCategory.CONCEPTUAL;
        }
        else if(category.equals("NUMERICAL")) {
            return QuestionCategory.NUMERICAL;
        }
        else if(category.equals("MEMORY_BASED")) {
            return QuestionCategory.MEMORY_BASED;
        }
        else {
            return QuestionCategory.CONCEPTUAL;
        }
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLevel() {
        if(level == 1) {
            return QuestionLevel.VERY_EASY;
        }
        else if(level == 2) {
            return QuestionLevel.EASY;
        }
        else if(level == 3) {
            return QuestionLevel.MEDIUM;
        }
        else if(level == 4) {
            return QuestionLevel.HARD;
        }
        else if(level == 5) {
            return QuestionLevel.VERY_HARD;
        }
        else {
            return QuestionLevel.MEDIUM;
        }
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public DiagramResponse getDiagram() {
        return diagram;
    }

    public void setDiagram(DiagramResponse diagram) {
        this.diagram = diagram;
    }

    @Override
    public String toString() {
        return "QuestionResponse{" +
                "question='" + question + '\'' +
                ", questionType='" + questionType + '\'' +
                ", options=" + Arrays.toString(options) +
                ", category='" + category + '\'' +
                ", level=" + level +
                ", instruction='" + instruction + '\'' +
                ", diagram=" + diagram +
                '}';
    }
}