package com.qedplus.particles.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OptionResponse {
    @JsonProperty("optionText") private String optionText;
    @JsonProperty("explanation") private String explanation;
    @JsonProperty("matchingOption") private String matchingOption;
    @JsonProperty("correct") private boolean correct;

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public String getExplanation() {
        return explanation
                .replace("<formula>", "\\( ")
                .replace("</formula>", " \\)")
                .replace("cfrac", "frac")
                .replace("\\implies", "\\Rightarrow");
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getMatchingOption() {
        return matchingOption;
    }

    public void setMatchingOption(String matchingOption) {
        this.matchingOption = matchingOption;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "OptionResponse{" +
                "optionText='" + optionText + '\'' +
                ", explanation='" + explanation + '\'' +
                ", matchingOption='" + matchingOption + '\'' +
                ", correct=" + correct +
                '}';
    }
}
