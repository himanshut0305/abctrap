package com.qedplus.particles.model;

import android.support.annotation.NonNull;

import com.qedplus.particles.extras.SlideType;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class RevisionSlideBO {
    public class BadSlideException extends Exception {
        public BadSlideException(String s) {
            super(s);
        }
    }

    private LinkedHashSet<RevisionPointBO> revisionPointBOS;
    private Question question;
    private ConfirmInteractionBO confirmInteraction;
    private RevealInteractionBO revealInteraction;
    private DiagramBO diagram;

    @SlideType.Type private String slideType;

    public RevisionSlideBO(LinkedHashSet<RevisionPointBO> revisionPointBOS) throws BadSlideException {
        if(revisionPointBOS == null)
            throw new NullPointerException("Revision Point Set is NULL");

        if(revisionPointBOS.isEmpty())
            throw new BadSlideException("Revision Point Set is Empty");

        if(revisionPointBOS.size() <= 2) {
            this.revisionPointBOS = revisionPointBOS;
            slideType = SlideType.REVISION_POINT;
        }
        else
            throw new BadSlideException("More than 2 Revision Points are not allowed in a Slide");
    }

    public RevisionSlideBO(Question question) {
        if(question == null)
            throw new NullPointerException("Question is NULL");

        this.question = question;
        slideType = SlideType.REVISION_QUESTION;
    }

    public RevisionSlideBO(LinkedHashSet<RevisionPointBO> revisionPointBOS, ConfirmInteractionBO confirmInteraction) {
        this.revisionPointBOS = revisionPointBOS;
        this.confirmInteraction = confirmInteraction;
        this.slideType = SlideType.REVISION_POINT;
    }

    public RevisionSlideBO(LinkedHashSet<RevisionPointBO> revisionPointBOS, RevealInteractionBO revealInteraction) {
        this.revisionPointBOS = revisionPointBOS;
        this.revealInteraction = revealInteraction;
        this.slideType = SlideType.REVISION_POINT;
    }

    public RevisionSlideBO(LinkedHashSet<RevisionPointBO> revisionPointBOS, DiagramBO diagram) {
        this.revisionPointBOS = revisionPointBOS;
        this.diagram = diagram;
        this.slideType = SlideType.REVISION_POINT;
    }

    @NonNull
    public Boolean isQuestionSlide() {
        return this.slideType.equals(SlideType.REVISION_QUESTION);
    }

    @NonNull
    public Boolean isPointSlide() {
        return this.slideType.equals(SlideType.REVISION_POINT);
    }

    public RevisionPointBO getFirstRevisionPoint() {
        Iterator<RevisionPointBO> i = revisionPointBOS.iterator();
        if(i.hasNext())
            return i.next();
        else
            return null;
    }

    public RevisionPointBO getSecondRevisionPoint() {
        Iterator<RevisionPointBO> i = revisionPointBOS.iterator();
        if(i.hasNext()) {
            i.next();
            if(i.hasNext())
                return i.next();
            else
                return null;
        }
        else
            return null;
    }

    public Question getQuestion() {
        return question;
    }

    public ConfirmInteractionBO getConfirmInteraction() {
        return confirmInteraction;
    }

    public RevealInteractionBO getRevealInteraction() {
        return revealInteraction;
    }

    public DiagramBO getDiagram() {
        return diagram;
    }

    @Override
    public String toString() {
        return "RevisionSlideBO{" +
                "revisionPointBOS=" + revisionPointBOS +
                ", question=" + question +
                ", confirmInteraction=" + confirmInteraction +
                ", revealInteraction=" + revealInteraction +
                ", diagram=" + diagram +
                ", slideType='" + slideType + '\'' +
                '}';
    }
}