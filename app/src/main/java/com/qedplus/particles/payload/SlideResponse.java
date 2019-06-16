package com.qedplus.particles.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qedplus.particles.db.RevisionPoint;
import com.qedplus.particles.extras.SlideType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SlideResponse {
    @JsonProperty("slideIndex") private int slideIndex;
    @JsonProperty("slideType") private String slideType;

    @JsonProperty("revisionPoint1") private RevisionPointResponse revisionPoint1;
    @JsonProperty("revisionPoint2") private RevisionPointResponse revisionPoint2;
    @JsonProperty("revealInteraction") private RevealInteractionResponse revealInteraction;

    @JsonProperty("confirmInteraction") private ConfirmInteractionResponse confirmInteraction;
    @JsonProperty("diagram") private DiagramResponse diagram;
    @JsonProperty("question") private QuestionResponse question;

    @JsonProperty("interactive") private boolean interactive;

    public int getSlideIndex() {
        return slideIndex;
    }

    public void setSlideIndex(int slideIndex) {
        this.slideIndex = slideIndex;
    }

    public String getSlideType() {
        return slideType;
    }

    public void setSlideType(String slideType) {
        this.slideType = slideType;
    }

    public RevisionPointResponse getRevisionPoint1() {
        return revisionPoint1;
    }

    public void setRevisionPoint1(RevisionPointResponse revisionPoint1) {
        this.revisionPoint1 = revisionPoint1;
    }

    public RevisionPointResponse getRevisionPoint2() {
        return revisionPoint2;
    }

    public void setRevisionPoint2(RevisionPointResponse revisionPoint2) {
        this.revisionPoint2 = revisionPoint2;
    }

    public RevealInteractionResponse getRevealInteraction() {
        return revealInteraction;
    }

    public void setRevealInteraction(RevealInteractionResponse revealInteraction) {
        this.revealInteraction = revealInteraction;
    }

    public ConfirmInteractionResponse getConfirmInteraction() {
        return confirmInteraction;
    }

    public void setConfirmInteraction(ConfirmInteractionResponse confirmInteraction) {
        this.confirmInteraction = confirmInteraction;
    }

    public DiagramResponse getDiagram() {
        return diagram;
    }

    public void setDiagram(DiagramResponse diagram) {
        this.diagram = diagram;
    }

    public QuestionResponse getQuestion() {
        return question;
    }

    public void setQuestion(QuestionResponse question) {
        this.question = question;
    }

    public String slideType() {
        if(slideType.equals("Question")) {
            return SlideType.REVISION_QUESTION;
        }
        else {
            return SlideType.REVISION_POINT;
        }
    }

    public boolean isInteractive() {
        return interactive;
    }

    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    }

    @Override
    public String toString() {
        return "SlideResponse{" +
                "slideIndex=" + slideIndex +
                ", slideType='" + slideType + '\'' +
                ", revisionPoint1=" + revisionPoint1 +
                ", revisionPoint2=" + revisionPoint2 +
                ", revealInteraction=" + revealInteraction +
                ", confirmInteraction=" + confirmInteraction +
                ", diagram=" + diagram +
                ", question=" + question +
                ", interactive=" + interactive +
                '}';
    }
}